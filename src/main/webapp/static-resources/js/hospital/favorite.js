 $(document).ready(function () {
var info = $(".info");
var hospitalId = info.find("input[name='hospitalId']");
var fh = $(".fav");
var bh = $(".favorite");

/*function showFavoriteHospitalList(page) {
	 favoriteService.getList({hospitalId: hospitalId.val()}, function (list) {
        var str = "";

        if (list == null || list.length == 0) {
        	
       	 alert("냉무");
       	 
        	fav.html(str);
        	return;
        }
        
fav.html("");
	 }); 
}  
*/
var h5 = $(".heart");

h5.on("click", "i", function(e){
	
	var icon = $(this).attr("data-i");
	/* 아이콘이 흰 하트면.. 검정 하트로 바뀌기 */
	if (icon=="white") {
		
		var str ="";
		
		str += "<i class='material-icons' style='cursor: pointer;'>event</i> 예약하기";	
		str += "<i class='material-icons favorite' data-i='black' id=filled style='cursor: pointer;'>favorite</i> 즐겨찾기"

		var info = $(".info");
		var hospitalId = info.find("input[name='hospitalId']");

		favoriteService.addFavorite({
			userEmail:"dummy@gmail.com", 
			hospitalId:hospitalId.val()}
				,
				function(result){
					alert("즐겨찾는 병원으로 등록되었습니다.");
					console.log("즐찾 등록 성공따리");

				});

	}

	
	/* 아이콘이 검정 하트면... 빈 하트로 바뀌기 and 삭제되기 */
	
	if (icon=="black") {
		var str ="";
		
		str += "<i class='material-icons' style='cursor: pointer;'>event</i> 예약하기";	
		str += "<i class='material-icons remove' data-i='white' id=outlined style='cursor: pointer;'>favorite_border</i> 즐겨찾기"

		var info = $(".info");
		var hospitalId = info.find("input[name='hospitalId']"); 


		favoriteService.removeFavorite(hospitalId.val(),
				function(result){ 
				alert("즐겨찾는 병원에서 삭제되었습니다.");
				console.log("즐찾 삭제 성공따리요");

 				}
			);
	}
             
	h5.html(str);
});

		
});