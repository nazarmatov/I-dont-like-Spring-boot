const API_URL = "http://localhost:8080/api/v1/characters";

let allCharacters = [];

function debounce(fn, delay = 200) {
    let timer;
    return (...args) => {
        clearTimeout(timer);
        timer = setTimeout(() => fn(...args), delay);
    };
}

async function loadCharacters() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            alert("Ошибка загрузки данных");
            return;
        }
        const json = await response.json();
        allCharacters = Array.isArray(json) ? json : [json];
        renderTable(allCharacters);
    } catch (err) {
        console.error(err);
        alert("Ошибка при запросе к серверу");
    }
}

function applyFilters() {
    const name = document.getElementById("nameFilter").value.trim().toLowerCase();
    const side = document.getElementById("sideFilter").value.trim().toLowerCase();

    const filtered = allCharacters.filter(c => {
        const cname = (c.name || "").toString().toLowerCase();
        const cside = (c.side || "").toString().toLowerCase();

        const nameMatches = !name || cname.includes(name);
        const sideMatches = !side || side === "all" || cside === side;

        return nameMatches && sideMatches;
    });

    renderTable(filtered);
}

function renderTable(characters) {
    const tbody = document.getElementById("tableBody");
    tbody.innerHTML = "";

    if (!characters.length) {
        tbody.insertAdjacentHTML("beforeend", `
            <tr>
                <td colspan="4" style="text-align:center;">Нет результатов</td>
            </tr>
        `);
        return;
    }

    characters.forEach(c => {
        tbody.insertAdjacentHTML("beforeend", `
            <tr>
                <td>${c.name ?? ""}</td>
                <td>${c.age ?? ""}</td>
                <td>${c.forcePower ?? ""}</td>
                <td>${c.side ?? ""}</td>
            </tr>
        `);
    });
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("nameFilter").addEventListener("input", debounce(applyFilters, 250));
    document.getElementById("sideFilter").addEventListener("change", applyFilters);
    loadCharacters();
});