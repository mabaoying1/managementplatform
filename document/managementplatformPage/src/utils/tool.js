
exports.install = function (Vue, options) {
    // X轴 title过长 换行 
    Vue.prototype.lineFeeds = function (params,x){
         /**********************
        * echart X轴title过长 自动换行
        * @Param: String params :被换行的字符串
        * @Param: Number x :每行多少个字符
        ************************/
        var newParamsName = "";// 最终拼接成的字符串
            var paramsNameNumber = params.length;// 实际标签的个数
            var provideNumber = x;// 每行能显示的字的个数
            var rowNumber = Math.ceil(paramsNameNumber / provideNumber);// 换行的话，需要显示几行，向上取整
            /**
             * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
             */
            // 条件等同于rowNumber>1
            if (paramsNameNumber > provideNumber) {
                /** 循环每一行,p表示行 */
                for (var p = 0; p < rowNumber; p++) {
                    var tempStr = "";// 表示每一次截取的字符串
                    var start = p * provideNumber;// 开始截取的位置
                    var end = start + provideNumber;// 结束截取的位置
                    // 此处特殊处理最后一行的索引值
                    if (p == rowNumber - 1) {
                        // 最后一次不换行
                        tempStr = params.substring(start, paramsNameNumber);
                    } else {
                        // 每一次拼接字符串并换行
                        tempStr = params.substring(start, end) + "\n";
                    }
                    newParamsName += tempStr;// 最终拼成的字符串
                }

            } else {
                // 将旧标签的值赋给新标签
                newParamsName = params;
            }
            //将最终的字符串返回
        return newParamsName
    };
    //跳转页面
    Vue.prototype.jump = function(url){
        /**********************
        * 跳转页面
        * @Param: String url :被跳转的地址
        ************************/
        this.$router.push(url)
    };
    //计算内屏高度
    Vue.prototype.aotoClientHeight = function (arr){
        /**********************
        * 计算指定的calss元素的高度
        * @Param: Array ：arr (calss元素)
        * @return obj:height分别高度，heightSum合计高度
        ************************/
       let arr_h = {
           height:[],
           heightSum:0
       }
        for(let i=0;i<arr.length;i++){
            let h = document.getElementsByClassName(arr[i])[0].clientHeight
            arr_h.height.push(h)
        };
        function sum(arr){
            return arr.reduce((previousValue ,currentValue)=>{
                return previousValue + currentValue
            })
        }
        arr_h.heightSum = sum(arr_h.height)

        return arr_h
    };
    /******************* 
    *   计算同环比
    *   jsonObj Obj 
    *   retun   Obj
    ********************/ 
    Vue.prototype.compute = function(jsonObj){

        
        
        // let jsonObj={
        //     // 组1
        //     MZFY_: "698820.55",//本期 100
        //     MZFY_MOM: "279607.90",//上一个周期 30 200

        //     //  （MZFY_ - MZFY_MOM ）/ MZFY_MOM

        //     MZFY_MOMGROWTH: "419212.65",//增量
        //     MZFY_MOMRATE: "1.50",//环比
        //     MZFY_YOY: "248268.22",//上一个周期 365
        //     MZFY_YOYGROWTH: "450552.33",//增量
        //     //  （MZFY_YOY - MZFY_YOYGROWTH）/ MZFY_YOYGROWTH
        //     MZFY_YOYRATE: "1.81",

        //     // 组2
        //     ZYFY_: "1487440.16",
        //     ZYFY_MOM: "1236144.45",
        //     ZYFY_MOMGROWTH: "251295.71",
        //     ZYFY_MOMRATE: "0.20",
        //     ZYFY_YOY: "1474858.71",
        //     ZYFY_YOYGROWTH: "12581.45",
        //     ZYFY_YOYRATE: "0.01",
        //     // 组N。。


        // }
        let new_Obj={}
           
        
        // 获取obj，的key值用于比较
        let jsonObj_item_name = Object.keys(jsonObj)

        // // 确定 一共几组数据
        let items = jsonObj_item_name.length / 7 
        
        // 项目分组命名规则
        let new_item_name = []
        jsonObj_item_name.forEach(item_name => {
            // console.log(item_name)
            let new_str=item_name.split("_")

            if(new_str[1]==''){
                // console.log(new_str)
                new_item_name.push(new_str[0])
            }
            
        });
        // console.log(new_item_name)
        
        // 创建新对象
        for (let i = 0;i<items ; i++) {
            new_Obj[ new_item_name[i] ] = {}
        }
        // 给新创建的对象赋值
        for (let i = 0;i<items; i++) {
            new_Obj[new_item_name[i]][new_item_name[i]] = jsonObj[`${new_item_name[i]}_`]
            new_Obj[new_item_name[i]]['MOM']=jsonObj[`${new_item_name[i]}_MOM`]
            new_Obj[new_item_name[i]]['MOMGROWTH']=jsonObj[`${new_item_name[i]}_MOMGROWTH`]
            new_Obj[new_item_name[i]]['MOMRATE']=jsonObj[`${new_item_name[i]}_MOMRATE`]
            new_Obj[new_item_name[i]]['YOY']=jsonObj[`${new_item_name[i]}_YOY`]
            new_Obj[new_item_name[i]]['YOYGROWTH']=jsonObj[`${new_item_name[i]}_YOYGROWTH`]
            new_Obj[new_item_name[i]]['YOYRATE']=jsonObj[`${new_item_name[i]}_YOYRATE`]
        }
        // console.info(new_Obj)

        // 计算同比/环比
        for (let i = 0; i < items; i++) {
            
            let mom =  ( new_Obj[new_item_name[i]][new_item_name[i]] - new_Obj[new_item_name[i]]['MOM'] ) / new_Obj[new_item_name[i]]['MOM']
            mom = mom.toFixed(3)
            new_Obj[new_item_name[i]]['MOMRATE'] = mom

            let yoy =  ( new_Obj[new_item_name[i]][new_item_name[i]] - new_Obj[new_item_name[i]]['YOY'] ) / new_Obj[new_item_name[i]]['YOY']
            yoy = yoy.toFixed(3)
            new_Obj[new_item_name[i]]['YOYRATE'] = yoy

        }
        console.info(new_Obj)
       
        return new_Obj
    }
};

