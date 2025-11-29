const API_URL = 'http://localhost:8080/product';
let idParaExcluir = null;

// Salvar produto
async function saveProduct(product) {

    try {
        const response = await fetch(API_URL , {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(product)
        })
      
         
        if (response.ok) {
        alert('Produto salvo com sucesso!');
        } else{
            const errorText = await response.text();
            console.log('Erro detalhado:', errorText);
        }
    } catch (error) {
        alert('Erro em salvar :' + error.message)
    }
}

// Buscar todos os produtos GET
async function getAllProducts() {
    try {
        const response = await fetch(API_URL);
        console.log('Status da resposta:', response.status);
        if (!response.ok) {
            throw new Error(`Erro ${response.status}: ${response.statusText}`);
        }
        const produtos = await response.json();
        console.log('Dados sendo enviados:', produtos);
        const lista = document.getElementById("listaProdutos");
        lista.innerHTML = "";

        produtos.forEach(p => {
        lista.innerHTML += `
        <div class="produto">
            <h2>${p.name}</h2>
            <p><strong>Fabricante:</strong> ${p.manufacturer}</p>
            <p><strong>Preço:</strong> R$ ${p.price.toFixed(2)}</p>
            <p>
                <strong>Quantidade:</strong> ${p.quantity}
                ${p.quantity <= 3 ? `<span class="estoque-baixo">Estoque baixo</span>` : ""}
            </p>
            <p>${p.description}</p>

            <button class="alterar" onclick="editarProduto(${p.id})">Alterar</button>
            <button class="excluir" onclick="abrirPopup(${p.id})">Excluir</button>
        </div>
    `;
    });

    } catch (error) {
        console.log('Erro ao buscar produtos:', error);
        return [];
    }
}

// Atualizar produto
async function updateProduct(id, product) {
    try {
        console.log("Id: " + id + " Produto:" + product.name)
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(product)
        });
        if (response.ok) {
        alert('Produto salvo com sucesso!');
        } else{
            const errorText = await response.text();
            console.log('Erro detalhado:', errorText);
        }
    } catch (error) {
        console.log('Erro ao atualizar:', error);
        return false;
    }
}

async function deleteProduct(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            alert('Produto excluído com sucesso!');
            getAllProducts(); 
        }
    } catch (error) {
        console.log('Erro ao deletar:', error);
        return false;
    }
}

function abrirPopup(id) {
    idParaExcluir = id;
    document.getElementById("mensagemPopup").innerText = `Deseja excluir o produto ID ${id}?`;
    document.getElementById("popup").style.display = "flex";
}

function fecharPopup() {
    document.getElementById("popup").style.display = "none";
}

function confirmarExclusao() {
    if (idParaExcluir) {
        deleteProduct(idParaExcluir); 
    }
    fecharPopup();
}

function editarProduto(id) {
    window.location.href = `newOrChange.html?id=${id}`;
}