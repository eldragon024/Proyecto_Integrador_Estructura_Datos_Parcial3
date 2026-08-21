const formulario = document.getElementById("asesoriaForm");
const resultado = document.getElementById("resultado");

formulario.addEventListener("submit", function (evento) {
    evento.preventDefault();

    const nombre = document.getElementById("nombre").value.trim();
    const correo = document.getElementById("correo").value.trim();
    const materia = document.getElementById("materia").value;
    const situacion = document.getElementById("situacion").value.trim();

    if (nombre === "" || correo === "" || materia === "" || situacion === "") {
        resultado.innerHTML = `
            <div class="result-placeholder">
                <div class="result-icon">!</div>
                <h3>Faltan datos</h3>
                <p>Completa todos los campos para analizar tu solicitud.</p>
            </div>
        `;
        return;
    }

    let titulo = "";
    let recomendacion = "";

    if (materia === "civil") {
        titulo = "Derecho Civil";
        recomendacion = "Tu solicitud fue clasificada como un asunto civil. Se recomienda continuar con una asesoría especializada en esta materia.";
    } else if (materia === "penal") {
        titulo = "Derecho Penal";
        recomendacion = "Tu solicitud fue clasificada como un asunto penal. Se recomienda buscar atención profesional especializada lo antes posible.";
    } else if (materia === "laboral") {
        titulo = "Derecho Laboral";
        recomendacion = "Tu solicitud fue clasificada como un asunto laboral. Se recomienda consultar con un profesional especializado en relaciones de trabajo.";
    }

    resultado.innerHTML = `
        <div class="result-success">
            <div class="result-icon">✓</div>
            <span class="eyebrow">SOLICITUD REGISTRADA</span>
            <h3>Hola, ${escapeHTML(nombre)}</h3>
            <p>La orientación inicial de tu solicitud corresponde a:</p>
            <h3>${titulo}</h3>
            <div class="recommendation">
                ${recomendacion}
            </div>
            <p><strong>Correo registrado:</strong> ${escapeHTML(correo)}</p>
            <p><strong>Descripción recibida:</strong> ${escapeHTML(situacion)}</p>
        </div>
    `;
});

function escapeHTML(texto) {
    const elemento = document.createElement("div");
    elemento.textContent = texto;
    return elemento.innerHTML;
}
