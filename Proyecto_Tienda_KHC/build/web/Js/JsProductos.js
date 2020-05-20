function RegistrarProducto(){
    var referencia = $("#referencia").val();
    var descripcion = $("#descripcion").val();
    var precio = $("#precio").val();
    var parametros = {'Operacion':'Registrar','referen':referencia,'descrip':descripcion,'precio':precio};
            
        $.ajax({
                type: 'POST',
                url: "Controlador_Productos",
                data: parametros,
                dataType: 'text',
                success: function (resp){
                    $("#referencia").val('');
                    $("#descripcion").val('');
                    $("#precio").val('');
                    $('#respuesta').html(resp);
                }
            }).fail(function(jqXHR, textStatus, errorThrown){
                alert("Error:"+jqXHR.Status);
            });
 }

 function listarProducto(){
     var Parametros = {'Operacion':'Listar'};
     $.ajax({
         type: 'POST',
         url: "Controlador_Productos",
         data: Parametros,
         dataType: 'text',
         success: function (resp){
             $("#formulario").hide();
              $("#mostrar").show();
             $('#ListaProductos').html(resp);
              
             
         }
     }).fail(function(jqXHR, textStatus, errorThrown){
         alert("Error:" + jqXHR.Status);
     });
 }

  /*
 function listarProducto()
        {
            var parametros = {'Operacion':'Listar'};
            $.ajax({
                type: 'POST',
                url: "Controlador_Productos",
                data: parametros,
                dataType: "text",
                success: function (resp){
                    var dato = JSON.parse(resp);
                     $('#table_id').DataTable({
                data : dato,
                columns : [
                    { data  : 'id_producto'},
                    { data  : 'referencia'},
                    { data  : 'precio'},
                    { data  : 'descripcion'},
                    { data  : 'eliminar'},
                    { data  : 'seleccionar'}
                ]
            });
                }
            }).fail(function(jqXHR, tetStatus, errorThrown){
                alert("Error:" + jqXHR.Status);
            });
        }
        
        */
 
 
 
 
 
 
 
 
 
 
 
 
 function EliminarProducto(id_producto){
     
     var parametros = {'Operacion':'EliminarPro','id_Produc':id_producto};
     $.ajax({
         type: 'POST',
         url: "Controlador_Productos",
         data: parametros,
         dataType: "text",
         success: function(resp){
             $('#respuesta').html(resp);
             listarProducto();
         }
     }).fail(function(jrXHR, textStatus, errorThrown){
         alert("Error: " + jrXHR.Status);
     });
     
 }
 function BuscarProductos(id){
     
     var parametros = {'Operacion':'BuscarProducto', 'id_Produc':id};
     $.ajax({
         type: 'POST',
         url: "Controlador_Productos",
         data: parametros,
         dataType: "text",
         success: function (resp){
             var dato= JSON.parse(resp);
                $.each(dato, function(producto, val){
                    $("#id_producto").val(val.id_producto);
                    $("#referencia").val(val.referencia);
                    $("#descripcion").val(val.descripcion);
                    $("#precio").val(val.precio);
                });
         }
     }).fail(function(jqXHR, textStatus, erroThrown){
         alert("Error: " + jqXHR.Status);
     });
 }
function ActualizarProducto(){
    var IdProducto = $("#id_producto").val();
    var referencia = $("#referencia").val();
    var descripcion = $("#descripcion").val();
    var precio = $("#precio").val();
    
     var parametros = {'Operacion':'Actualizar','referen':referencia,'descrip':descripcion,'pre':precio,'id_produc':IdProducto};
     $.ajax({
         type: 'POST',
         url:"Controlador_Productos",
         data: parametros,
         dataType: "text",
         success: function (resp){
            listarProducto();
            $("#id_producto").val('');
            $("#referencia").val('');
            $("#descripcion").val('');
            $("#precio").val('');
            $('#respuesta').html(resp);
            $("#formulario").hide();
              $("#mostrar").show();
         }
     }).fail(function(jqXHR, textStatus, errorTrhown){
         alert("Error:" + jqXHR.Status);
     });
}

