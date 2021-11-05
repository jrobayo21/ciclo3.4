
function consultar(){
    
    $.ajax({
        url:"http://129.146.198.67:8080/api/Cabin/all",
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
	myTable+="<tr>" + "<th>CABAÑA<th>CUARTOS<th>NOMBRE<th>DESCRIPCIÓN<th>CATEGORIA</th>"+"</tr>";
    for(i=0;i<respuesta.length;i++){
		
        myTable+="<tr>";
        myTable+="<td>"+respuesta[i].brand+"</td>";
        myTable+="<td>"+respuesta[i].rooms+"</td>";
		myTable+="<td>"+respuesta[i].name+"</td>";
		myTable+="<td>"+respuesta[i].description+"</td>";
		myTable+="<td>"+respuesta[i].categoryId+"</td>";
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
		brand: $("#brand").val(),
		rooms: $("#rooms").val(),
		name: $("#name").val(),
		description: $("#description").val(),
		categoryId: $("#categoryId").val(),
        };
      	
        $.ajax({
		url:"http://129.146.198.67:8080/api/Cabin/save",
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
		brand: $("#brand").val(),
		rooms: $("#rooms").val(),
		name: $("#name").val(),
		description: $("#description").val(),
		categoryId: $("#categoryId").val(),

    };
    console.log(myData);
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url:"http://129.146.198.67:8080/api/Cabin/update",
        type:"PUT",
        data:dataToSend,
        contentType:"application/JSON",
        datatype:"JSON",
        success:function(respuesta){
            $("#resultado").empty();
            $("#id").val("");
            $("#brand").val("");
            $("#rooms").val
			$("#name").val("");
			$("#description").val("");
			$("#categoryId").val("");
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
        url:"http://129.146.198.67:8080/api/Cabin/"+idElemento,
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