
function consultar(){
    
    $.ajax({
        url:"http://129.146.198.67:8080/api/Reservation/all",
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
	myTable+="<tr>" + "<th>FECHA DE INICIO<th>FECHA DE FINALIZACIÓN</th>"+"</tr>";
    for(i=0;i<respuesta.length;i++){
		
        myTable+="<tr>";
        myTable+="<tr>";
        myTable+="<td>"+respuesta[i].startDate+"</td>";
        myTable+="<td>"+respuesta[i].devolutionDate+"</td>";
        myTable+="<td> <button class='btn btn-outline-success btn-min-width mr-1 mb-1 btn-sm' onclick=' actualizar("+respuesta[i].idReservation+")'>Actualizar</button>";
        myTable+="<td> <button class='btn btn-outline-success btn-min-width mr-1 mb-1 btn-sm' onclick='borrar("+respuesta[i].idReservation+")'>Borrar</button>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultado").html(myTable);
}

function guardar(){
    let var2 = {
        idReservation: $("#idReservation").val(),
		startDate: $("#startDate").val(),
		devolutionDate: $("#devolutionDate").val(),
		 };
	
        $.ajax({
		url:"http://129.146.198.67:8080/api/Reservation/save",
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
        idReservation:idElemento,
		startDate: $("#startDate").val(),
		devolutionDate: $("#devolutionDate").val()

    };
    console.log(myData);
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url:"http://129.146.198.67:8080/api/Reservation/update",
        type:"PUT",
        data:dataToSend,
        contentType:"application/JSON",
        datatype:"JSON",
        success:function(respuesta){
            $("#resultado").empty();
            $("#idReservation").val("");
			$("#startDate").val("");
			$("#devolutionDate").val("");
			consultar();
            alert("se ha Actualizado correctamente la categoria")
        },
		error: function(jqXHR, textStatus, errorThrown) {
            
      },
		
    });

}

function borrar(idElemento){
    let myData={
        idReservation:idElemento
    };
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url:"http://129.146.198.67:8080/api/Reservation/"+idElemento,
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