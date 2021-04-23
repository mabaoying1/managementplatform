//保留两位小数 百分比 例 36.61%
let leftTwoDecimal = (value)=>{
    if(value != 0){
        return `${(value*100).toFixed(2)}%`
    }else{
        return `0%`
    }
    
}
//保留两位小数
let rightTwoDecimal = (value)=>{
    return `${(value).toFixed(2)}`
}
//切割数字，每三位切割 ，例 68685.32  --》 68,685
let incisionLeft = (num1)=>{
    //去掉小数部分
    let num = parseInt(num1)
    var result = [ ], counter = 0;
    num = (num || 0).toString().split('');
    for (var i = num.length - 1; i >= 0; i--) {
        counter++;
        result.unshift(num[i]);
        if (!(counter % 3) && i != 0) { result.unshift(','); }
    }
    return result.join('');
}
//获取小数
let incisionRight = (num1)=>{
    let right = (num1+'').split('.')

    return !!right[1]   ?    `.${right[1]}` :''
}
//保留整数  且 保留两位小数
let incisionTwo = (num1)=>{
    let right = (num1+'').split('.'),_l
    if(right[1] ){
        _l = Math.abs(right[1].substr(0, 2))
    }else{
        _l = '00'
    }
    return `${right[0]}.${_l}`
}
export{
    leftTwoDecimal,
    rightTwoDecimal,
    incisionLeft,
    incisionRight,
    incisionTwo
}