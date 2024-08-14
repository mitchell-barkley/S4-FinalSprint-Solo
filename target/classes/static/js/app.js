console.log('app.js loaded');

document.addEventListener('DOMContentLoaded', () => {
    const button = document.getElementById('prev');
    button.addEventListener('click', () => {
        fetchBinaryTreeData();
    });
});
function fetchBinaryTreeData() {
    fetch('/trees')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            displayBinaryTreeData(data);
        })
        .catch(error => console.error('Error fetching data:', error));
}

function displayBinaryTreeData(data) {
    const container = document.getElementById('binaryTreeDisplay');
    container.innerHTML = '';
    if (data.length === 0) {
        container.innerHTML = '<p>No data found.</p>';
        return;
    }
    data.forEach(tree => {
        const treeElement = document.createElement('div');
        treeElement.textContent = `Tree ID: ${tree.id}, Tree Structure: ${tree.treeStructure}`;
        container.appendChild(treeElement);
    });
}