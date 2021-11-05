
function consultar(){
    
    $.ajax({
        url:"http://129.146.198.67:8080/api/Category/all",
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
	myTable+="<tr>" + "<th>NOMBRE<th>DESCRIPCIÓN</th>"+"</tr>";
    for(i=0;i<respuesta.length;i++){
		
        myTable+="<tr>";
        myTable+="<tr>";
        myTable+="<td>"+respuesta[i].name+"</td>";
        myTable+="<td>"+respuesta[i].description+"</td>";
        myTable+="<td> <button class='btn btn-outline-success btn-min-width mr-1 mb-1 btn-sm' onclick=' actualizar("+respuesta[i].id+")'>Actualizar</button>";
        myTable+="<td> <button class='btn btn-outline-success btn-min-width mr-1 mb-1 btn-sm' onclick='borrar("+respuesta[i].id+")'>Borrar</button>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultado").html(myTable);
}

function guardar(){
    let var2 = {
        id: $("#id").val(),
		name: $("#name").val(),
		description: $("#description").val(),
		 };
	
        $.ajax({
		url:"http://129.146.198.67:8080/api/Category/save",
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
        id:idElemento,
		name: $("#name").val(),
		description: $("#description").val()

    };
    console.log(myData);
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url:"http://129.146.198.67:8080/api/Category/update",
        type:"PUT",
        data:dataToSend,
        contentType:"application/JSON",
        datatype:"JSON",
        success:function(respuesta){
            $("#resultado").empty();
            $("#id").val("");
			$("#name").val("");
			$("#description").val("");
			consultar();
            alert("se ha Actualizado correctamente la categoria")
        },
		error: function(jqXHR, textStatus, errorThrown) {
            
      },
		
    });

}

function borrar(idElemento){
    let myData={
        id:idElemento
    };
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url:"http://129.146.198.67:8080/api/Category/"+idElemento,
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