﻿$(document).ready(function () {
    $('.popUpImage').click(function (event) {
        event.preventDefault();
        $(".modal img").attr("src", $(this).attr('href'));
        $(".modal").modal('show');
    });
});

$('.custom-file-input').on('change', function (event) {
    var inputFile = event.currentTarget;
    $(inputFile).parent()
        .find('.custom-file-label')
        .html(inputFile.files[0].name);
}); 

$(document).on('click', '.number-spinner button', function () {
    var btn = $(this),
        oldValue = btn.closest('.number-spinner').find('input').val().trim(),
        newVal = 0;

    if (btn.attr('data-dir') == 'up') {
        newVal = parseInt(oldValue) + 1;
    } else {
        if (oldValue > 1) {
            newVal = parseInt(oldValue) - 1;
        } else {
            newVal = 1;
        }
    }
    btn.closest('.number-spinner').find('input').val(newVal);
});

$(function () {
    $('#dataTable').DataTable({
        "order": [[0, "asc"]],
        "lengthMenu": [[15, 30, 45, -1], [15, 30, 45, "All"]]
    });
});

$(function () {
    $('#formularioProducto').validate({
        rules: {
            nombre: {
                required: true,
                minlength: 10,
                maxlength: 100
            },
            descripcion: {
                required: true,
                minlength: 60,
                maxlength: 600
            },
            precio: {
                required: true,
                number:true,
                min: 1
            },
            stock: {
                required: true,
                number: true,
                min: 0
            },
            marca: {
                required: true,
            },
            categoria: {
                required: true,
            }

        },
        messages: {
            nombre: {
                required: "Este campo es obligatorio",
                minlength: "El nombre debe tener mínimo 10 caracteres",
                maxlength: "El nombre debe tener máximo 100 caracteres"
            },
            descripcion: {
                required: "Este campo es obligatorio",
                minlength: "El nombre debe tener mínimo 60 caracteres",
                maxlength: "El nombre debe tener máximo 600 caracteres"
            },
            precio: {
                required: "Este campo es obligatorio",
                min: "El precio debe ser mayor que 0.00",
                number: "Debe ingresar un número decimal"
            },
            stock: {
                required: "Este campo es obligatorio",
                min: "El stock debe ser mayor o igual que 0",
                number: "Debe ingresar un número entero"
            },
            marca: {
                required: "Este campo es obligatorio",
            },
            categoria: {
                required: "Este campo es obligatorio",
            },
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });
});

function ConfirmarDelete(e, codigo) {
    e.preventDefault();
    Swal.fire({
        title: '¿Seguro que deseas eliminar?',
        icon: 'warning',
        showCancelButton: true,
        cancelButtonText: "Cancelar",
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '¡Sí, Elimínalo!',
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Se actualizo el estado a "Eliminado"',
                showConfirmButton: false,
                timer: 1500
            })
            setTimeout(function () { window.location.href = 'eliminar/' + codigo ; }, 1500);
        }
    })
}

$(function () {
    $('#formularioProductoRegistro').validate({
        rules: {
            fotoProducto: {
                required: true
            },
            nombre: {
                required: true,
                minlength: 10,
                maxlength: 100
            },
            descripcion: {
                required: true,
                minlength: 60,
                maxlength: 600
            },
            precio: {
                required: true,
                number: true,
                min: 1
            },
            stock: {
                required: true,
                number: true,
                min: 0
            },
            marca: {
                required: true,
            },
            categoria: {
                required: true,
            },
             estado: {
            	required: true,
            }
        },
        messages: {
            fotoProducto: {
                required: "Este campo es obligatorio"
            },
            nombre: {
                required: "Este campo es obligatorio",
                minlength: "El nombre debe tener mínimo 10 caracteres",
                maxlength: "El nombre debe tener máximo 100 caracteres"
            },
            descripcion: {
                required: "Este campo es obligatorio",
                minlength: "El nombre debe tener mínimo 60 caracteres",
                maxlength: "El nombre debe tener máximo 600 caracteres"
            },
            precio: {
                required: "Este campo es obligatorio",
                min: "El precio debe ser mayor que 0.00",
                number: "Debe ingresar un número decimal"
            },
            stock: {
                required: "Este campo es obligatorio",
                min: "El stock debe ser mayor o igual que 0",
                number: "Debe ingresar un número entero"
            },
            marca: {
                required: "Este campo es obligatorio"
            },
            categoria: {
                required: "Este campo es obligatorio"
            },
             estado: {
                required: "Este campo es obligatorio"
            },
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });
});

$(function () {
    $('#formularioMarca').validate({
        rules: {
            nombre: {
                required: true,
                minlength: 2,
                maxlength: 30
            },
            estado: {
            	required: true,
            }
        },
        messages: {
            nombre: {
                required: "Este campo es obligatorio",
                minlength: "El nombre debe tener mínimo 2 caracteres",
                maxlength: "El nombre debe tener máximo 30 caracteres"
            },
            estado: {
                required: "Este campo es obligatorio",
            },
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });
});

$(function () {
    $('#formularioLogin').validate({
        rules: {
            correo: {
                required: true,
                email: true
            },
            clave: {
                required: true,
                minlength: 5,
            }

        },
        messages: {
            correo: {
                required: "Este campo es obligatorio",
                email: "Debe ingresar un correo electrónico válido",
            },
            clave: {
                required: "Este campo es obligatorio",
                minlength: "La clave debe tener mínimo 5 caracteres",
            }
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });
});
