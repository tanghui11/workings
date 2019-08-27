// for(let i=0;i<10;i++){
//     console.log("循环体中："+i)
// }
// console.log("循环体外："+i)
// let [a,b,c] = [0,1,2];
// console.log(a)
// console.log(b)
// console.log(c)
// let [foo='true']=[];
// console.log(foo)
// 
// let {foo,bar}={foo:"th",bar:"唐辉"};
// console.log(foo+bar);
/*let foo;
({foo} = {foo:"123"});
console.log(foo)*/
/* const [a,b,c,d,e,f] = 'thqwer';
console.log(a)
console.log(b)
console.log(c)
console.log(d)
console.log(e)
console.log(f)*/


//对象扩展运算符
// function th(...arg){
//     console.log(arg[0])
//     console.log(arg[1])
//     console.log(arg[2])
//     console.log(arg[3])
// }
// th(1,2,3)
// let arr1 = ['www','th','com'];
// //let arr2 = arr1;
// let arr2=[...arr1];
// console.log(arr2);
// arr2.push("tanghui");
// console.log(arr2);
// console.log(arr1);


//rest ...
// function th(frist,...arg){
//    // console.log(arg.length);
//    for(let val of arg){
//        console.log(val);
//    }
// }
// th(0,1,2,3,4,5,6,7,8)

//字符串模板
// let th = "tanghui";
// let blog= "我的姓名叫"+th+",好听的名字";
// console.log(blog)
// let th = "tanghui";
// let blog= `我的姓名叫${th},好听的tanghui`
// console.log(blog)
// console.log(blog.includes(th))
// console.log(blog.startsWith(th))
// console.log(blog.endsWith(th))
// document.write(th.repeat(10))


//二进制申明 Binary
// let binary = 0B010101;
// console.log(binary);
// //八进制申明  Octal
// let octal = 0o666;
// console.log(octal)

// //判断是否是数字
// let a =11/4;
// console.log(Number.isFinite(a))
// console.log(Number.isFinite("th"))
// console.log(Number.isFinite(NaN))
// console.log(Number.isFinite(undefined))

// //判断NaN
// console.log(Number.isNaN(1));


// //json数组格式
// let json = {
//     '0':'tanghui',
//     '1':'ta',
//     '2':'heihei',
//     length:3
// }
// let arr = Array.from(json);
// console.log(arr);

//Array.of方法
//
// let arr = Array.of(1,2,3,4);
// console.log(arr)

//find()实列方法
// let arr = [1,2,3,4,5,6,7,8,9];
// console.log(arr.find(function(value,index,arr){
//     return index >5;
// }))


//fill
// let arr = ["th","唐辉","唐军"];
// console.log()
// arr.fill('web',1,2);
// console.log(arr)


//数组循环
//entries
// let arr = ["th","唐辉","唐军"];
// for (let [index,val] of arr.entries()) {
//     console.log(index+":"+val)
//     //console.log()
// }
// let list  = arr.entries();
// console.log(list.next().value);
// console.log("----------------");
// console.log(list.next().value);
// console.log("****************");
// console.log(list.next().value);
// console.log("================");

//箭头函数
// function add(a=2,b=1){
//     return a+b;
// }
// console.log(add(1));
// var add = (a,b=1)=>{
//     console.log("tanghui")
//     return a+b;
// }
// console.log(add(1))

//函数和数组补漏
//对象的函数解构
// let json = {
//     a:"th",
//     b:"唐辉",
// }
// var fun = ({a,b='web'})=>{
//     console.log(a,b)
// }
// fun(json);

//数组解构
// let arr=['th','唐辉','前端教程'];
// var fun1 = (a,b,c)=>{
//     console.log(a,b,c)
// }
// fun1(...arr)


//in的用法
// let obj = {
//     a:"th",
//     b:"唐辉",
// }
// console.log("a" in obj)
// let arr =['th',,,];
// console.log(0 in arr)


//数组遍历
//forEach
let arr = ["th","tanghui","唐辉"];
// arr.forEach(function(val,index){
//     console.log(val+" "+index)
// })
arr.forEach((val,index) => {
    console.log(val+" "+index);  
});
//filter
arr.filter((val,index)=>{
    console.log(val+" "+index);
})
//some
arr.some((val,index)=>{
    console.log(val+" "+index);
})
//map