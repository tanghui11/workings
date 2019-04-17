package com.hxy.nzxy.stexam.common.controller;

import com.alibaba.fastjson.JSON;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.system.domain.ExportFieldDO;
import com.hxy.nzxy.stexam.system.domain.ExportTableDO;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.system.service.ExportFieldService;
import com.hxy.nzxy.stexam.system.service.ExportTableService;
import com.linuxense.javadbf.DBFDataType;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFUtils;
import com.linuxense.javadbf.DBFWriter;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/common")
@Controller
public class CommonController extends BaseController {
    @Autowired
    CommonService commonService;
    @Autowired
    private ExportTableService exportTableService;
    @Autowired
    private ExportFieldService exportFieldService;

    /**
     * 根据关键字返回用户信息
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/listUserByKey")
    List<UserDO> list(@RequestParam Map<String, Object> params) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (params.containsKey("query")) {
            map.put("key", params.get("query").toString());
        }
        if (params.containsKey("key")) {
            map.put("key", params.get("key").toString());
        }
        if (params.containsKey("limit")) {
            if (params.get("limit").toString().matches("[0-9]+")) {
                map.put("limit", Integer.parseInt(params.get("limit").toString()));
            } else {
                map.put("limit", 10);
            }
        }
        List<UserDO> page = commonService.listUserByKey(map);
        return page;
    }
    /**
     *根据表名称,和所传字段判断
     *
     * @param   tableName
     * @param  fileName
     * @param  fileValue
     * @param  method
     * @return
     */


    @GetMapping("/searchIfExist")
    @ResponseBody
    public int searchIfExist(@RequestParam Map<String, Object> map, HttpServletRequest request) {
      /*  String tableName = (String)map.get("tableName");
        String fileName1 = (String)map.get("filedName1");
        String fileValue1 = (String)map.get("filedValue1");
        String method = (String)map.get("method");
        String updateKeyValue = (String)map.get("updateKeyValue");
        String updateKey = (String)map.get("updateKey");*/
        return commonService.searchIfExist(map);
    }
    /**
     *验证 同一期是学生只能注册一次
     *
     * @param   tableName
     * @param  fileName
     * @param  fileValue
     * @param  method
     * @return
     */

    @GetMapping("/searchStudentidCard")
    @ResponseBody
    public int searchStudentidCard(@RequestParam Map<String, Object> map, HttpServletRequest request) {

        return commonService.selectIDCardStudentid(map);
    }



    @ResponseBody
    @RequestMapping("/exportDbf")
    public void exportDbf(HttpServletResponse response,
                          HttpServletRequest request,
                          @ModelAttribute("exportTableName") String exportTableName,
                          @ModelAttribute("exportFileName") String exportDbfName,
                          @ModelAttribute("exportParam") Map<String, Object> exportParam) throws IOException {
        ExportTableDO exportTableDO = commonService.getExportTableByName(exportTableName);
        if (exportTableDO == null || exportTableDO.getId() == null) {
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("没有找到：[" + exportTableName + "]导出的数据定义!");
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }
        List<ExportFieldDO> exportFieldList = commonService.listExportFieldByExportTableid(exportTableDO.getId().toString());
        if (exportFieldList == null || exportFieldList.size() == 0) {
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("没有找到：[" + exportTableName + "]导出的数据字段定义!");
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }
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
            if (str.equalsIgnoreCase("numeric") || str.equalsIgnoreCase("integer")) {
                fields[i].setType(DBFDataType.NUMERIC);
            } else if (str.equalsIgnoreCase("char") || str.equalsIgnoreCase("memo")) {
                fields[i].setType(DBFDataType.CHARACTER);
            } else if (str.equalsIgnoreCase("date")) {
                fields[i].setType(DBFDataType.DATE);
            } else if (str.equalsIgnoreCase("float")) {
                fields[i].setType(DBFDataType.FLOATING_POINT);
            }
            if (exportFieldList.get(i).getLength() != null) {
                if (exportFieldList.get(i).getLength() >= 254) {
                    fields[i].setLength(254);
                } else {
                    fields[i].setLength(exportFieldList.get(i).getLength());
                }
            }
        }
        DBFWriter writer = null;
        try {
            response.setContentType("application/vnd.ms-dbf;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(exportDbfName.getBytes("GB2312"), "ISO8859-1") + ".dbf");
            OutputStream ouputStream = response.getOutputStream();
            writer = new DBFWriter(ouputStream);
            writer.setFields(fields);//写入表头
            writer.setCharactersetName("GBK");
            //查询数据
            String sql = exportTableDO.getQuerySql();
            //替换参数
            for (Map.Entry<String, Object> entry : exportParam.entrySet()) {
                sql = sql.replace(entry.getKey(), entry.getValue().toString());
            }
            List<Map<String, String>> list = exportTableService.tablelist(sql);
            for (Map maplist : list) {
                Object rowData[] = new Object[fields.length];
                //遍历每一列
                for (int j = 0; j < fields.length; j++) {
                    for (Object key : maplist.keySet()) {
                        if (fields[j].getName().equals(key.toString())) {
                            //转义
                            String value = maplist.get(key).toString();
                            if (exportFieldList.get(j).getTransType().equals("field_dict")) {
                                value = FieldDictUtil.get(getAppName(), exportFieldList.get(j).getTableName(), exportFieldList.get(j).getFieldName(), value);
                            }
                            //转义机构id
                            if (exportFieldList.get(j).getTransType().equals("org")) {
                                value = OrgUtil.getName(value);
                            }
                            //转义代码
                            if (exportFieldList.get(j).getTransType().equals("org_code")) {
                                value = OrgUtil.getCode(value);
                            }
                            //转义用户id
                            if (exportFieldList.get(j).getTransType().equals("user")) {
                                value = UserUtil.getName(value);
                            }
                            //
                            if (fields[j].getType().equals(DBFDataType.NUMERIC)) {
                                //String转为数值
                                rowData[j] = Long.parseLong(value);
                            } else if (fields[j].getType().equals(DBFDataType.DATE)) {
                                //String转为时间
                                String dateStr = value;
                                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    Date date = sf.parse(dateStr);
                                    rowData[j] = date;
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            } else if (fields[j].getType().equals(DBFDataType.FLOATING_POINT)) {
                                //String转为Float
                                rowData[j] = Float.parseFloat(value);
                            } else {
                                rowData[j] = value;
                            }
                        }
                    }
                }
                writer.addRecord(rowData);
            }
        } catch (UnsupportedEncodingException e) {
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("导出数据时发生错误!错误原因："+e.getMessage());
            response.getWriter().flush();
            response.getWriter().close();
            return;
        } catch (IOException e) {
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("导出数据时发生错误!错误原因："+e.getMessage());
            response.getWriter().flush();
            response.getWriter().close();
            return;
        } finally {
            DBFUtils.close(writer);
        }
    }

    @ResponseBody
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response,
                            HttpServletRequest request,
                            @ModelAttribute("exportTableName") String exportTableName,
                            @ModelAttribute("exportFileName") String exportExcelName,
                            @ModelAttribute("exportParam") Map<String, Object> exportParam) throws IOException {
        ExportTableDO exportTableDO = commonService.getExportTableByName(exportTableName);
        if (exportTableDO == null || exportTableDO.getId() == null) {
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("没有找到：[" + exportTableName + "]导出的数据定义!");
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }
        List<ExportFieldDO> exportFieldList = commonService.listExportFieldByExportTableid(exportTableDO.getId().toString());
        if (exportFieldList == null || exportFieldList.size() == 0) {
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("没有找到：[" + exportTableName + "]导出的数据字段定义!");
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }
        //设置表头
        Map<Integer, String> titleMap = new HashedMap();
        Map<String, Integer> keyMap = new HashedMap();
        for (int i = 0; i < exportFieldList.size(); i++) {
            titleMap.put(i, exportFieldList.get(i).getName());
            keyMap.put(exportFieldList.get(i).getName(), i);
        }
        List<Map> datas = new ArrayList<>();
        //插入数据
        String sql = exportTableDO.getQuerySql();
        //替换参数
        for (Map.Entry<String, Object> entry : exportParam.entrySet()) {
            sql = sql.replace(entry.getKey(), entry.getValue().toString());
        }
        List<Map<String, String>> list = exportTableService.tablelist(sql);
        for (Map maplist : list) {
            Map<Integer, Object> dataMap = new HashedMap();
            //遍历每一列
            for (Object key : maplist.keySet()) {
                String value = maplist.get(key).toString();
                if (keyMap.containsKey(key)) {
                    //转义数据字典
                    if (exportFieldList.get(keyMap.get(key.toString())).getTransType().equals("field_dict")) {
                        value = FieldDictUtil.get(getAppName(), exportFieldList.get(keyMap.get(key.toString())).getTableName(), exportFieldList.get(keyMap.get(key.toString())).getFieldName(), value);
                    }
                    //转义机构名称
                    if (exportFieldList.get(keyMap.get(key.toString())).getTransType().equals("org")) {
                        value = OrgUtil.getName(value);
                    }
                    //转义机构代码
                    if (exportFieldList.get(keyMap.get(key.toString())).getTransType().equals("org_code")) {
                        value = OrgUtil.getCode(value);
                    }
                    //转义用户名称
                    if (exportFieldList.get(keyMap.get(key.toString())).getTransType().equals("user")) {
                        value = UserUtil.getName(value);
                    }
                }
                dataMap.put(keyMap.get(key.toString()), value);
            }
            datas.add(dataMap);
        }

        XSSFWorkbook hssfWorkbook = ExcelGenerator.generate(titleMap, datas, exportExcelName);
        if (hssfWorkbook == null) {
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("生成Excel文件是发生错误!");
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }
        String fileName = hssfWorkbook.getSheetName(0);
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(exportExcelName.getBytes("GB2312"), "ISO8859-1") + ".xls");
            OutputStream ouputStream = response.getOutputStream();
            hssfWorkbook.write(ouputStream);
        } catch (IOException e) {
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.getWriter().write("导出Excel文件是发生错误!\\n" + e.getMessage());
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }
    }
    /**
     * 批量审核公共接口接口(危险)
     */
    @PostMapping( "/batchAuditStatus")
    @ResponseBody
    public R batchAuditStatus(@RequestParam Map<String, Object> map){
        Long[] KeyValue= (Long[]) map.get("ids");
        String  tableName="";String  filedName="";
        String  filedValue="";String  Key="";
        if(KeyValue.length>0){
            commonService.batchAuditStatus(tableName,filedName,filedValue,Key,KeyValue);
            return R.ok();
        }else{
            return R.ok("请选择要进行处理的数据");
        }


    }
    /**
     * 进度条刷新，数据从session当中取
     */
    @RequestMapping(value = "/flush")
    @ResponseBody
    public String flush(HttpServletRequest request) throws Exception
    {
        HashMap<String,Object> map=null;
        try {
            HttpSession session = request.getSession();
            map=new HashMap<String, Object>();
            map.put("totalCount", session.getAttribute("totalCount"));  //总条数
            map.put("curCount", session.getAttribute("curCount"));      //已导条数
            map.put("percent", session.getAttribute("percent"));          //百分比数字
            map.put("percentText", session.getAttribute("percentText"));  //百分比文本
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }
}
