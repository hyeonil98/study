document.addEventListener("DOMContentLoaded", () => {
    const testBtn = document.getElementById("testBtn");

    testBtn.addEventListener("click", async () => {
        try {
            const response = await fetch("/api/v1/test");
            if (response.ok) {
                const data = await response.json();
                alert(`Test successful: ${data.message}`);
            } else {
                const errorData = await response.json();
                alert(`Error: ${errorData.message}`);
            }
        }
        catch (error) {
            console.error("Error during test:", error);
            alert("An unexpected error occurred. Please try again later.");
        }
    });
});

async function register() {
    const form = document.getElementById("registerForm");
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    try {
        const response = await fetch("/api/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert("Registration successful!");
            window.location.href = "/login";
        } else {
            const errorData = await response.json();
            alert(`Error: ${errorData.message}`);
        }
    } catch (error) {
        console.error("Error during registration:", error);
        alert("An unexpected error occurred. Please try again later.");
    }
}

function fileUpload() {
    const fileInput = document.getElementById("fileUploadBtn");
    const file = fileInput.files[0];

    if (!file) {
        alert("Please select a file to upload.");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    fetch("/api/v1/fileUpload", {
        method: "POST",
        body: formData
    })
    .then(response => {
        if (response.ok) {
            alert("File uploaded successfully!");
        } else {
            return response.json().then(errorData => {
                throw new Error(errorData.message);
            });
        }
    })
    .catch(error => {
        console.error("Error during file upload:", error);
        alert(`Error: ${error.message}`);
    });
}

function excelDownload() {
    fetch("/api/v1/excelDownload")
        .then(response => {
            if (response.ok) {
                return response.blob();
            } else {
                return response.json().then(errorData => {
                    throw new Error(errorData.message);
                });
            }
        })
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement("a");
            a.href = url;
            let date = new Date();
            date = date.toISOString().split('T')[0]; // Format date as YYYY-MM-DD
            a.download = date+"_test.xlsx";
            document.body.appendChild(a);
            a.click();
            a.remove();
        })
        .catch(error => {
            console.error("Error during Excel download:", error);
            alert(`Error: ${error.message}`);
        });
}