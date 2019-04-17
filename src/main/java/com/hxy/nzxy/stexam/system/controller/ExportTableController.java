package com.hxy.nzxy.stexam.system.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;
import java.util.Date;

import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.domain.ExportFieldDO;
import com.hxy.nzxy.stexam.system.service.ExportFieldService;
import com.linuxense.javadbf.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.system.domain.ExportTableDO;
import com.hxy.nzxy.stexam.system.service.ExportTableService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 导出数据表名
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-03-16 16:14:20
 */

@Controller
@RequestMapping("/system/exportTable")
public class ExportTableController extends SystemBaseController {
    @Autowired
    private ExportTableService exportTableService;

    @Autowired
    private CommonService commonService;

    @Value("${stexam.exportTable.exportTableUrl}")
    private String exportTablePath;

    @GetMapping()
    @RequiresPermissions("system:exportTable:exportTable")
    String ExportTable(Model model) {
        model.addAttribute("exportTable_list", exportTableService.list());
        return "system/exportTable/exportTable";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:exportTable:exportTable")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ExportTableDO> exportTableList = exportTableService.list(query);
        for (ExportTableDO item : exportTableList) {
            item.setType(FieldDictUtil.get(getAppName(), "sys_export_table", "type", item.getType()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = exportTableService.count(query);
        PageUtils pageUtils = new PageUtils(exportTableList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:exportTable:add")
    String add(Model model) {
        model.addAttribute("export_table_type", commonService.listFieldDict(getAppName(), "sys_export_table", "type"));
        return "system/exportTable/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:exportTable:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        ExportTableDO exportTable = exportTableService.get(id);
        model.addAttribute("export_table_type", commonService.listFieldDict(getAppName(), "sys_export_table", "type"));
        exportTable.setType(FieldDictUtil.get(getAppName(), "sys_export_table", "type", exportTable.getType()));
        model.addAttribute("exportTable", exportTable);
        return "system/exportTable/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:exportTable:add")
    public R save(ExportTableDO exportTable) {
        exportTable.setOperator(getUserId().toString());
        if (exportTableService.save(exportTable) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:exportTable:edit")
    public R update(ExportTableDO exportTable) {
        exportTable.setOperator(getUserId().toString());
        exportTableService.update(exportTable);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:exportTable:remove")
    public R remove(Long id) {
        if (exportTableService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @Autowired
    private ExportFieldService exportFieldService;

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:exportTable:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        exportTableService.batchRemove(ids);
        return R.ok();
    }

    /**
     * 导出数据表
     */
    @GetMapping("/exportData")
    @ResponseBody
    @RequiresPermissions("system:exportTable:exportData")
    public R exportTable(@RequestParam("id") Long id, HttpServletResponse response) throws DBFException, IOException {
        ExportTableDO exportTableDO = exportTableService.get(id);
        Map<String, Object> map = new HashMap<>();
        map.put("exportTableid", id);
        List<ExportFieldDO> exportFieldList = exportFieldService.list(map);
        //设置表头
        DBFField fields[] = new DBFField[exportFieldList.size()];
        for (int i = 0; i < exportFieldList.size(); i++) {
            fields[i] = new DBFField();
            String strName = exportFieldList.get(i).getName();
            if (strName.length() > 10) {
                strName = strName.substring(0, 10);
            }
            fields[i].setName(strName);
            String str = exportFieldList.get(i).getType();
            if (str.equalsIgnoreCase("numeric") || str.equalsIgnoreCase("integer") ) {
                fields[i].setType(DBFDataType.NUMERIC);
            } else if (str.equalsIgnoreCase("char") || str.equalsIgnoreCase("memo")) {
                fields[i].setType(DBFDataType.CHARACTER);
            } else if (str.equalsIgnoreCase("date")) {
                fields[i].setType(DBFDataType.DATE);
            } else if (str.equalsIgnoreCase("float")) {
                fields[i].setType(DBFDataType.FLOATING_POINT);
            }
            if (exportFieldList.get(i).getLength() != null) {
                fields[i].setLength(exportFieldList.get(i).getLength());
            }
        }
        DBFWriter writer = null;
        FileOutputStream fos = null;
        //设置文件名
        String strFileName = exportTableDO.getName() + ".dbf";
        //如果没有这个路径创建
        File path = new File(exportTablePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        //在路径下创建新的文件
        File f = new File(exportTablePath + strFileName);
        f.createNewFile();
        try {
            // 开始写
            fos = new FileOutputStream(f);
            writer = new DBFWriter(fos);
            writer.setFields(fields);//写入表头
            writer.setCharactersetName("GB2312");
            //插入数据
            String sql = exportTableDO.getQuerySql();
            List<Map<String, String>> list = exportTableService.tablelist(sql);
            for (Map maplist : list) {
                Object rowData[] = new Object[fields.length];
                for (int j = 0; j < fields.length; j++) {
                    for (Object key : maplist.keySet()) {
                        if (fields[j].getName().equals(key.toString())) {
                            if (fields[j].getType().equals(DBFDataType.NUMERIC)) {
                                //String转为数值
                                rowData[j] = Long.parseLong(maplist.get(key).toString());
                            } else if (fields[j].getType().equals(DBFDataType.DATE)) {
                                //String转为时间
                                String dateStr = maplist.get(key).toString();
                                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    Date date = sf.parse(dateStr);
                                    rowData[j] = date;
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            } else if (fields[j].getType().equals(DBFDataType.FLOATING_POINT)) {
                                //String转为Float
                                rowData[j] = Float.parseFloat(maplist.get(key).toString());
                            } else {
                                rowData[j] = maplist.get(key);
                            }
                        }
                    }
                }
                writer.addRecord(rowData);
            }
            DBFUtils.close(writer);
        } finally {
            DBFUtils.close(writer);
        }
        R ref = new R();
        ref.put("url", "/exportTableData/" + strFileName);
        return ref;
    }
}
