<!DOCTYPE html>
<html lang="en">
<head>
  <title>Empresa CLARO</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav  class="nav nav-tabs" >
  <div class="container-fluid">
    <div class="navbar-header">
      <b class="navbar-brand" href="#">CLARO</b>
    </div>
    <ul class="nav navbar-nav">
     <li class="active" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Cargar Archivos <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li>
          	<div class="input-group">
			  <div class="input-group-prepend">
			    <span data-toggle="tab" href="#menu1">Cargar Lineas Telefonicas</span>
			  </div>
			  <div class="custom-file">
			    <input type="file" class="custom-file-input" id="inputGroupFile01"
			      aria-describedby="inputGroupFileAddon01">
			    <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
			  </div>
			</div>
          </li>
          <li>
          	<div class="input-group">
			  <div class="input-group-prepend">
			    <span data-toggle="tab" href="#menu2">Cargar CDR</span>
			  </div>
			  <div class="custom-file">
			    <input type="file" class="custom-file-input" id="inputGroupFile01"
			      aria-describedby="inputGroupFileAddon01">
			    <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
			 	 </div>
				</div>
			</li>
        </ul>
      </li>
      <li ><a data-toggle="tab" href="#menu3" >Tarificar</a></li>
     
      <li><a data-toggle="tab" href="#menu4" >Ver Tarificacion</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Configuracion <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a data-toggle="tab" href="#menu5" >Persistir a Archivos de texto</a></li>
          <li><a data-toggle="tab" href="#menu6">Persisitir a Archivo SQL</a></li>
        </ul>
      </li>
    </ul>
  </div>
</nav>
 <div class="tab-content">
    <div id="menu1" class="tab-pane fade">
      <h3>Menu 1</h3>
    </div>
    <div id="menu2" class="tab-pane fade">
      <h3>Menu 2</h3>
    </div>
    <div id="menu3" class="tab-pane fade">
      <h3>Menu 3</h3>
      <p>Aca muestra la tabla anteriormente cargada pero ya tarificada.</p>
    </div>
    <div id="menu4" class="tab-pane fade">
      <h3>Menu 4</h3>
      <p>Muestran todas las tarificaciones</p>
    </div>
    <div id="menu5" class="tab-pane fade">
      <h3>Menu 5</h3>
      <p>Muestra alguna verificacion de que se hizo el cambio a Archivos de texto</p>
    </div>
    <div id="menu6" class="tab-pane fade">
      <h3>Menu 6</h3>
      <p>Muestra la verificacion de que se hizo el cambio a SQL</p>
    </div>
  </div>
</div>
</body>
</html>