var hys = {};

hys.index = 0;
hys.bannerImg = $(".banner-img");
hys.bannerLength = hys.bannerImg.length;
hys.changeLine = $(".change-line");
hys.arrowLeft  = $(".arrow-left");
hys.arrowRight = $(".arrow-right");

hys.autoSlide = ()=>{
    if (hys.index === hys.bannerLength ){
        hys.index = 0;
    }
    for (var i = 0; i < hys.bannerLength; i++) {
        if (hys.index === 0){
            hys.bannerImg.eq(hys.bannerLength-1).css({
                opacity:1
            })
        } else{
            hys.bannerImg.eq(hys.index-1).css({
                opacity:1
            })
        }
        hys.bannerImg.eq(i).css({
            opacity:"0",
            transition:""
        });
    }
    if (hys.index === 0) {
        hys.bannerImg.eq(hys.bannerLength-1).css({
            opacity: 0,
            transition: "opacity 1s ease-in-out"
        }).end().eq(0).css({
            opacity: 1,
            transition: "opacity 1s ease-in-out"
        });
    } else {
        hys.bannerImg.eq(hys.index).css({
            opacity: 1,
            transition: "opacity 1s ease-in-out"
        }).end().eq(hys.index-1).css({
            opacity: 0,
            transition: "opacity 1s ease-in-out"
        });
    }
    for (var i1 = 0;i1 < hys.changeLine.length;i1++){
        if (hys.changeLine.eq(i1).hasClass("change-line-on")){
            hys.changeLine.eq(i1).removeClass("change-line-on");
        }
    }
    hys.changeLine.eq(hys.index).addClass("change-line-on");

    hys.index++;
    console.log("autoSlide hys.index "+ hys.index);

}

hys.autoSlide();

(()=>{
    if (!hys.timer) {
        hys.timer = setInterval(hys.autoSlide, 8000);
    }
})()

hys.arrowLeft.click(() => {
    hys.index--;
    for (var i = 0; i < hys.bannerLength; i++) {
        if (hys.index === 0){
            hys.bannerImg.eq(hys.bannerLength-1).css({
                opacity:1
            })
        } else{
            hys.bannerImg.eq(hys.index-1).css({
                opacity:1
            })
        }
        hys.bannerImg.eq(i).css({
            opacity:"0",
            transition:""
        });
    }
    if (hys.index === 0) {
        hys.bannerImg.eq(hys.bannerLength - 1).css({
            opacity:1,
            transition:"opacity 1s ease-in-out"
        }).end().eq(hys.index).css({
            opacity:0,
            transition:"opacity 1s ease-in-out"
        })
        hys.index = hys.bannerLength - 1;
    }else {
        hys.bannerImg.eq(hys.index).css({
            opacity:0,
            transition:"opacity 1s ease-in-out"

        }).end().eq(hys.index-1).css({
            opacity:1,
            transition:"opacity 1s ease-in-out"
        })
        hys.index --;
    }
    console.log(hys.index);
    for (var i1 = 0;i1 < hys.changeLine.length;i1++){
        if (hys.changeLine.eq(i1).hasClass("change-line-on")){
            hys.changeLine.eq(i1).removeClass("change-line-on");
        }
    }
    hys.changeLine.eq(hys.index).addClass("change-line-on");
    if (hys.timer){
        clearInterval(hys.timer);
    }
});
hys.arrowRight.click(() => {
    hys.autoSlide();
});
