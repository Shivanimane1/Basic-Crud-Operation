$(document).ready(function() {
    alert("loading...");

    $("#btn").click(function(event) {
        event.preventDefault(); // Prevent default form submission
        ajaxPost();
    });

    function ajaxPost() {
        var formData = {
            name: $("#name").val(),
            description: $("#description").val(),
            category: $("#category").val(),
            price: $("#price").val(),
            quantity: $("#quantity").val()
        };

        $.ajax({
            type: "POST",
            url: "/save_products",
            data: formData,
           // contentType: "application/json",
            success: function(response) {
                alert("added successfully...");
                ajaxGet(); // Fetch updated data after successful addition
            },
            error: function(xhr, status, error) {
                alert("not added successfully...");
            }
        });
    }

    // Your other AJAX functions here...
});


$(document).ready(function() {

	$("#form").submit(function(event) {
		event.preventDefault();
		ajaxGet();
		alert("save successfully...")

	});
});
//console.log("save table");


function ajaxGet() {

	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/add_product",
		//data: JSON.stringify(data),
		dataType: 'json',

		success: function(data) {
			alert("data retrived...");
			console.log("data");

			var d = '';

			for (var i = 0; i < data.length; i++) {

				d += '<tr>' +
					'<td > ' + data[i].id + '</td>' +
					'<td > ' + data[i].name + '</td>' +
					'<td > ' + data[i].description + '</td>' +
					'<td > ' + data[i].category + '</td>' +
					'<td > ' + data[i].price + '</td>' +
					'<td > ' + data[i].quantity + '</td>' +
					'<td > <button data-bs-toggle="modal" data-bs-target="#editData"  data-product-id="' + data[i].id + '"  id="editBtn-' + data[i].id + '" class="btn btn-info">Edit</button> ' +
					'<button   data-product-id="' + data[i].id + '" id="deleteBtn-' + data[i].id + '" class="btn btn-danger">Delete</button>' + '</td>' +
					'</tr >';
			}
			$('#table').html(d);
		},
		error: function(e) {
			alert("Not Working..");
		}
	});
}
ajaxGet();

//edit button


$(document).on('click', '[id^="editBtn-"]', function() {

	alert("Do you want to change data ?")

	let id = $(this).data('product-id');

	console.log("ID is : " + id);



	$.ajax({

		type: "GET",

		contentType: "application/json",

		url: "/get_product/" + id,

		dataType: 'json',

		success: function(data) {
			console.log("Data received:",data);
			
			alert("loading");

			if (data) {

				console.log("Product id is : " + data.id);
				console.log("Product name is : " + data.name);
				console.log("Product description is : " + data.description);
				console.log("Product category is : " + data.category);
				console.log("Product price is : " + data.price);
				console.log("Product quantity is : " + data.quantity);


				$("#id").val(data.id);
				$("#name1").val(data.name);
				$("#description1").val(data.description);
				$("#category1").val(data.category);
				$("#price1").val(data.price);
				$("#quantity1").val(data.quantity);

			}
			console.log(data);

		},

		error: function(e) {

			console.log("Error in feching data for Id....");

		}
	});

});
//Update data
    
$("#saveForm").on('click', function(e){

	alert("Updatingg data ...");
	e.preventDefault();

	
	let id = $("#id").val();
	let name = $("#name1").val();
	let description = $("#description1").val();
	let category = $("#category1").val();
	let price= $("#price1").val();
	let quantity= $("#quantity1").val();

	
	let updatedData = {

		id : id,
		name : name,
		description:description,
		category:category,
		price:price,
		quantity:quantity
	};

	console.log(updatedData);

		$.ajax({

				type: "PUT",

				contentType: "application/json",

				url: "/editData",

				data: JSON.stringify(updatedData),

				dataType: 'json',

				success: function (response) {

					alert("Updated Sucessfully...");

					ajaxGet();

				},

				error: function (e) {

					alert("Not Working..");

				}

			});

});



$(document).on('click', '[id^="deleteBtn-"]', function() {

	

	alert("Do you want to delete record.....");

	let id = $(this).data('product-id');

	console.log("Id is : " + id);

		

		$.ajax({

			type: "DELETE",

			contentType: "application/json",

			url:   "/deleteData/"+id,

			success: function (response){

				alert("Deleted Successfully...");

				ajaxGet();

			},

			error: function(e){

				alert("Details not deleted...");

			}

		});

	

	});


    
    
    
    

