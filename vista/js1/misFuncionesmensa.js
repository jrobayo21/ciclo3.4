
function consultar(){
    
    $.ajax({
        url:"http://129.146.198.67:8090/api/Message/all",
        type:"GET",
        datatype:"JSON",
        success:function(respuesta){
            console.log(respuesta);
            pintarRespuesta(respuesta);
			
        }
    
    });

}


function pintarRespuesta(respuesta){

    let myTable="<table>";
	myTable+="<tr>" + "<th>MENSAJE</th>"+"</tr>";
    for(i=0;i<respuesta.length;i++){
		
        myTable+="<tr>";
        myTable+="<tr>";
        myTable+="<td>"+respuesta[i].messageText+"</td>";
        myTable+="<td> <button class='btn btn-outline-success btn-min-width mr-1 mb-1 btn-sm' onclick=' actualizar("+respuesta[i].idMessage+")'>Actualizar</button>";
        myTable+="<td> <button class='btn btn-outline-success btn-min-width mr-1 mb-1 btn-sm' onclick='borrar("+respuesta[i].idMessage+")'>Borrar</button>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultado").html(myTable);
}

function guardar(){
    let var2 = {
        idMessage: $("#idMessage").val(),
		messageText: $("#messageText").val(),
		 };
	
        $.ajax({
		url:"http://129.146.198.67:8090/api/Message/save",
         type:'POST',
        contentType: "application/json",
        dataType: 'JSON',
        data: JSON.stringify(var2),
       
        success:function(response) {
           console.log(response);
			
            alert("Se guardo correctamente");
            
    
        },
        
        error: function(jqXHR, textStatus, errorThrown) {
             
            alert("No se guardo correctamente");
			
    
    
        }
        });

}


function actualizar(idElemento){
    let myData={
        idMessage:idElemento,
		messageText: $("#messageText").val()

    };
    console.log(myData);
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url:"http://129.146.198.67:8090/api/Message/update",
        type:"PUT",
        data:dataToSend,
        contentType:"application/JSON",
        datatype:"JSON",
        success:function(respuesta){
            $("#resultado").empty();
            $("#idMessage").val("");
			$("#messageText").val("");
			consultar();
            alert("se ha Actualizado correctamente la categoria")
        },
		error: function(jqXHR, textStatus, errorThrown) {
            
      },
		
    });

}

function borrar(idElemento){
    let myData={
        idMessage:idElemento
    };
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url:"http://129.146.198.67:8090/api/Message/"+idElemento,
        type:"DELETE",
        data:dataToSend,
        contentType:"application/JSON",
        datatype:"JSON",
        success:function(respuesta){
            $("#resultado").empty();
            consultar();
            alert("Se ha Eliminado.")
        }
    });

}