# QlibraryAPI
API para digitalizar todos los libros de una biblioteca.

	Se  desarrollara un API para cubrir la siguiente necesidad de digitalizar todos los libros de una biblioteca.
 
	Condiciones a cumplir:
	
		● Los usuarios del sistema serán del personal de la biblioteca que deben registrarse con un nombre, un correo y una contraseña 
		  (no es necesario	validar cuenta, pero el usuario debe recibir un correo de confirmación).

		● Solo usuarios registrados pueden registrar nuevos libros y autores de libros
		
		● Los libros pueden tener múltiples autores.
		
		● Los libros pueden tener múltiples categorías.
		
		● Cualquier usuario (registrado o anónimo) puede consultar los libros.
		
		● Cualquier usuario (registrado o anónimo) puede consultar todos los libros de un autor.

		● Cualquier usuario (registrado o anónimo) puede consultar todos los libros de una categoría
		
		● Cualquier usuario (registrado o anónimo) puede consultar los datos de un autor.
		
		● La solución debe permitir generar un excel de reporte donde indique: Total de libros, total de autores, 
		total de categorías, total de libros por cada categoría de libro.



ER logico:

	Tablas:

	1	Usuarios: Esta tabla almacenará la información de los usuarios registrados en la aplicación. Los atributos de esta tabla serán:

			* id_usuario: Identificador único del usuario.
			* nombre: Nombre del usuario.
			* correo: Correo electrónico del usuario.
			* contraseña: Contraseña del usuario.

	2	Autores: Esta tabla almacenará la información de los autores de los libros. Los atributos de esta tabla serán:

			* id_autor: Identificador único del autor.
			* nombre: Nombre del autor.
			* apellido: Apellido del autor.
			* pais_origen: País de origen del autor.
			* fecha_nacimiento: Fecha de nacimiento del autor.
			* id_usuario: usuario que crea al autor.
		
	3	Categorías: Esta tabla almacenará la información de las categorías de los libros. Los atributos de esta tabla serán:

			* id_categoria: Identificador único de la categoría.
			* nombre: Nombre de la categoría.

	
	4	Libros: Esta tabla almacenará la información de los libros. Los atributos de esta tabla serán:

		 	* id_libro: Identificador único del libro.
		 	* titulo: Título del libro.
		 	* fecha_publicacion: Fecha de publicación del libro.
		 	* id_categoria: Identificador de la categoría del libro.
		 	* descripcion: Descripción del libro.
		 	* id_usuario: usuario que crea al libro


	5	Libro_autor: esta tabla almacenara la relacion entre el autor y el libro, de forma que se puedan tener libros con varios autores.

			* id_libro: Identificador único del libro.
			* id_autor: Identificador único del autor.

	6	Libro_categoria: esta tabla almacenara la relacion entre el categoria y el libro, de forma que se puedan tener libros con varias categorias.

			* id_libro: Identificador único del libro.
			* id_categoria: Identificador único de la categoría.


	tablas adicionales: 

	7	Rol : esta tabla almacenara la informacion de los roles en la aplicacion.

			*id_rol:Identificador único del Rol.
			*nombre: Nombre asociado al rol 


	8	usuario_rol: esta tabla almacenara la informacion que relaciona a un usuario con un rol, permitiendo que un usuario registrado pueda tener multiples roles. 

		*id_rol:Identificador único del Rol.
		* id_usuario: Identificador único del usuario.

