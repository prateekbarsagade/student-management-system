const BASE_URL = "http://localhost:8080/app/api/students";

window.onload = function () {
    fetchStudents();
};

// Fetch All Students
function fetchStudents() {
    fetch(BASE_URL)
        .then(res => res.json())
        .then(data => {
            const table = document.getElementById("studentTable");
            table.innerHTML = "";

            data.forEach(student => {
                table.innerHTML += `
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.email}</td>
                        <td>${student.age}</td>
                        <td>
                            <button class="edit-btn" onclick="editStudent(${student.id})">Edit</button>
                            <button class="delete-btn" onclick="deleteStudent(${student.id})">Delete</button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(error => console.error("Error fetching students:", error));
}

// Create or Update Student
function saveStudent() {

    const id = document.getElementById("studentId").value;
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const age = document.getElementById("age").value;

    const student = { name, email, age };

    if (id) {
        // Update
        fetch(`${BASE_URL}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(student)
        }).then(() => {
            resetForm();
            fetchStudents();
        });

    } else {
        // Create
        fetch(BASE_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(student)
        }).then(() => {
            resetForm();
            fetchStudents();
        });
    }
}

// Edit Student
function editStudent(id) {
    fetch(`${BASE_URL}/${id}`)
        .then(res => res.json())
        .then(student => {
            document.getElementById("studentId").value = student.id;
            document.getElementById("name").value = student.name;
            document.getElementById("email").value = student.email;
            document.getElementById("age").value = student.age;
        });
}

// Delete Student
function deleteStudent(id) {
    fetch(`${BASE_URL}/${id}`, {
        method: "DELETE"
    }).then(() => fetchStudents());
}

// Reset Form
function resetForm() {
    document.getElementById("studentId").value = "";
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("age").value = "";
}