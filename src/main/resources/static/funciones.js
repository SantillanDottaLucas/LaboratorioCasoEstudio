document.addEventListener("DOMContentLoaded", () => {
    // 1. Saludo Dinámico
    const saludoDinamico = document.getElementById("saludo-dinamico");
    if (saludoDinamico) {
        const hora = new Date().getHours();
        let mensaje = "Gestión del Sistema";

        if (hora >= 6 && hora < 12) {
            mensaje = "¡Buen día! Gestión del catálogo";
        } else if (hora >= 12 && hora < 20) {
            mensaje = "¡Buenas tardes! Gestión del catálogo";
        } else {
            mensaje = "¡Buenas noches! Gestión del catálogo";
        }
        saludoDinamico.textContent = mensaje;
    }

    // 2. Desvanecer alertas automáticamente (Alerts de Bootstrap)
    const alertas = document.querySelectorAll('.alert-dismissible');
    alertas.forEach(alerta => {
        setTimeout(() => {
            alerta.classList.remove('show');
            alerta.classList.add('fade');
            setTimeout(() => alerta.remove(), 1500);
        }, 4000); // 4 seg
    });
});

// 3. Confirmación de borrado 
function confirmarBorrado(event, nombreElemento) {
    const seguro = confirm(`¿Estás seguro de que querés eliminar "${nombreElemento}"?`);
    if (!seguro) {
        event.preventDefault(); // Cancela el viaje al controlador si se arrepiente
        return false;
    }
    return true;
}
