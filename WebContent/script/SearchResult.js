$(".studentContainer").click(function(){
         $.ajax({
            url:'view',
            data:{idNum: $(this).find('p:second').attr('id')},	// Second <p>
            type:'get',
            cache:false,
            success:function(data){
            	console.log("Done searchResult.js");
            },
            error:function(){
            	console.log("error searchResult.js");
            }
         });
         
     System.out.println("ASKJLAKSLSALK");
     alert("ASAS");
});
       