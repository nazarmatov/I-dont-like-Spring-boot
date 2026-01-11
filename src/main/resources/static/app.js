const API_URL = "http://localhost:8080/api/v1/characters";

async function loadCharacters() {
    const name = document.getElementById("nameFilter").value;
    const side = document.getElementById("sideFilter").value;

    let url = API_URL;

    if (name) {
        url += `/${name}`;
    } else if (side) {
        url += `/side/${side}`;
    }

    const response = await fetch(url);

    if (!response.ok) {
        alert("Error loading data");
        return;
    }

    const data = name ? [await response.json()] : await response.json();
    renderTable(data);
}

function renderTable(characters) {
    const tbody = document.getElementById("tableBody");
    tbody.innerHTML = "";

    characters.forEach(c => {
        const row = `
            <tr>
                <td>${c.name}</td>
                <td>${c.age}</td>
                <td>${c.forcePower}</td>
                <td>${c.side}</td>
            </tr>
        `;
        tbody.insertAdjacentHTML("beforeend", row);
    });
}

loadCharacters();