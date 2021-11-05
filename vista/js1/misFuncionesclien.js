
function consultar(){
    
    $.ajax({
        url:"http://129.146.198.67:8090/api/Client/all",
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
	myTable+="<tr>" + "<th>NOMBRE<th>EMAIL<th>CONTRASEÑA<th>EDAD</th>"+"</tr>";
    for(i=0;i<respuesta.length;i++){
		
        myTable+="<tr>";
        myTable+="<tr>";
        myTable+="<td>"+respuesta[i].name+"</td>";
        myTable+="<td>"+respuesta[i].email+"</td>";
		myTable+="<td>"+respuesta[i].password+"</td>";
		myTable+="<td>"+respuesta[i].age+"</td>";
        myTable+="<td> <button class='btn btn-outline-success btn-min-width mr-1 mb-1 btn-sm' onclick=' actualizar("+respuesta[i].idClient+")'>Actualizar</button>";
        myTable+="<td> <button class='btn btn-outline-success btn-min-width mr-1 mb-1 btn-sm' onclick='borrar("+respuesta[i].idClient+")'>Borrar</button>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultado").html(myTable);
}

function guardar(){
    let var2 = {
        idClient: $("#idClient").val(),
		name: $("#name").val(),
		email: $("#email").val(),
		password: $("#password").val(),
		age: $("#age").val()
		 };
	
        $.ajax({
		url:"http://129.146.198.67:8090/api/Client/save",
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
        idClient:idElemento,
		name: $("#name").val(),
		email: $("#email").val(),
		password: $("#password").val(),
		age: $("#age").val()

    };
    console.log(myData);
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url:"http://129.146.198.67:8090/api/Client/update",
        type:"PUT",
        data:dataToSend,
        contentType:"application/JSON",
        datatype:"JSON",
        success:function(respuesta){
            $("#resultado").empty();
            $("#idClient").val("");
			$("#name").val("");
			$("#email").val("");
			$("#password").val("");
			$("#age").val("");
			consultar();
            alert("se ha Actualizado correctamente la categoria")
        },
		error: function(jqXHR, textStatus, errorThrown) {
            
      },
		
    });

}

function borrar(idElemento){
    let myData={
        idClient:idElemento
    };
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url:"http://129.146.198.67:8090/api/Client/"+idElemento,
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