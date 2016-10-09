define( function(require, exports, module) {
    //引用jQuery模块
    var $ = require('jquery');
    // 得到初始化的画布
    // 
    var width = $("#matchImage").parent().width();
    $("#matchImage").attr("width",width);
    var canvas = document.getElementById("matchImage");
    var canvasWidth = canvas.width;
    //  获取中点
    var middlePoint = Math.round(canvasWidth / 2);
    // 初始化数据
    var canvasHeight = 0;
    var matchPercent = 0.3; //  30%
    var textPercent = 0.05; //  5%
    var spacePercent = 0.01;//  1%
    /**
     * [calculatePosition description]
     * @param  {[type]} backgroundWith   [背景宽度]
     * @param  {[type]} backgroundHeigth [背景高度]
     * @return {[type]}                  [图片的宽高;和home和visit开始点]
     */
    var calculatePosition = function(backgroundWith ,backgroundHeigth){
        var matchPicWidth =  Math.round(middlePoint * matchPercent);
        var homeBeginX = Math.round((middlePoint - matchPicWidth) / 2);
        var homeBeginY = Math.round((backgroundHeigth - matchPicWidth) / 2);
        var visitBeginX = middlePoint + homeBeginX;
        var visitBeginY = homeBeginY;
        return {
                homeBeginX: homeBeginX,
                homeBeginY: homeBeginY,
                visitBeginX: visitBeginX,
                visitBeginY: visitBeginY,
                picSize : matchPicWidth
            };
    };
    
    var calculateVSTextPosition = function(backgroundWith , backgroundHeigth){
        var textSize = Math.round(backgroundWith * textPercent);
        var spaceSize = Math.round(backgroundWith * spacePercent);
        //var textY = Math.round((backgroundHeigth - textSize) / 2);
        // 应该有问题 后面可以测试
        var textY = Math.round((backgroundHeigth) / 2);

        var textFirstX = middlePoint - spaceSize - textSize;
        var textSecondX = middlePoint + spaceSize ;
        return {
            textFirstX : textFirstX,
            textFirstY : textY,
            textSecondX : textSecondX,
            textSecondY : textY,
            textSize    : textSize
        };
    };

    /**
     * [renderImage 比赛背景图片]
     * @param  {[type]} ctx  
     * @param  {[type]} picUrl  
     * @param  {[type]} beginX      
     * @param  {[type]} beginY      
     * @param  {[type]} picSize   
     * @return {[type]}           [description]
     */
    var drawMatchImage = function(ctx , picUrl,beginX, beginY , picSize){
        var homeImage = new Image();
        homeImage.src = picUrl;
        homeImage.onload = function(){
            var startPointX = beginX;
            var startPointY = beginY;
            ctx.drawImage(homeImage, startPointX, startPointY, picSize, picSize);
        };
        homeImage.onerror = function(){
            alert("图片加载不成功！");
            console.log("图片加载不成功！");
            return false;
        };
    };
    
    
    module.exports = {
        /**
         * [renderImage 比赛背景图片]
         * @param  {[URL]} background [背景图片]
         * @param  {[URL]} home       [组队图片]
         * @param  {[URL]} visit      [客队图片]
         * @param  {[text]} matchText [比赛信息 such as VS or 比分]
         * @return {[type]}            [description]
         */
     	renderImage: function( backgroundUrl, homeUrl , visitUrl , matchText){
              var backgroundWith = 0;
              var backgroundHeigth = 0;
              if (canvas.getContext){
                ctx = canvas.getContext('2d');
                var backgroundImage = new Image();
                
                if(backgroundUrl == ""){
                    alert("背景地址为空！");
                    console.log("背景地址为空！");
                    return false;
                }
                backgroundImage.src = backgroundUrl;
                //背景图片加载
                backgroundImage.onload = function(){
                    backgroundWith = backgroundImage.width;
                    // console.log(backgroundImage.width);
                    backgroundHeigth = backgroundImage.height;
                    canvasHeight = Math.round(canvasWidth / backgroundWith * backgroundHeigth);
                    // 设置高度
                    $("#matchImage").attr("height",canvasHeight);
                    ctx.drawImage(backgroundImage, 0, 0, canvasWidth, canvasHeight);
                    //
                    //homeBeginX
                    //homeBeginY
                    //visitBeginX
                    //visitBeginY
                    //picSize (正方形)
                    //
                    var matchImageDrawInfo =  calculatePosition(canvasWidth,canvasHeight);

                   console.log(matchImageDrawInfo.homeBeginX + " | " + matchImageDrawInfo.homeBeginY + " | " + matchImageDrawInfo.visitBeginX + " | " + matchImageDrawInfo.visitBeginY + " | " + matchImageDrawInfo.picSize);
                    // 加载 主队图片
                    
                    if(homeUrl == ""){
                        alert("主队图片为空！");
                        console.log("主队地址为空！");
                        return false;
                    }
                    if(visitUrl == ""){
                        alert("客队图片为空！");
                        console.log("客队图片为空！");
                        return false;
                    };
                    drawMatchImage(ctx , homeUrl ,matchImageDrawInfo.homeBeginX ,matchImageDrawInfo.homeBeginY,matchImageDrawInfo.picSize);
                    drawMatchImage(ctx , visitUrl ,matchImageDrawInfo.visitBeginX ,matchImageDrawInfo.visitBeginY,matchImageDrawInfo.picSize);
                    // 这里有待改进   
                    var textVal = calculateVSTextPosition(canvasWidth,canvasHeight);
                    ctx.font = textVal.textSize + "PX Arial";
                    ctx.fillText("V" ,textVal.textFirstX ,textVal.textFirstY );
                    ctx.fillText("S" ,textVal.textSecondX ,textVal.textFirstY );  
                };
                backgroundImage.onerror =function(){
                    alert("图片加载不成功！");
                    return false;
                };
            }
     	}
     }
});