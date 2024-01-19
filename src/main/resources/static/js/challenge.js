$(document).ready(function () {
    //Toggle fullscreen
    $("#panel-fullscreen").click(function (e) {
        e.preventDefault();

        var $this = $(this);

        if ($this.children('i').hasClass('glyphicon-resize-full'))
        {
            $this.children('i').removeClass('glyphicon-resize-full');
            $this.children('i').addClass('glyphicon-resize-small');
        }
        else if ($this.children('i').hasClass('glyphicon-resize-small'))
        {
            $this.children('i').removeClass('glyphicon-resize-small');
            $this.children('i').addClass('glyphicon-resize-full');
        }
        $(this).closest('.panel').toggleClass('panel-fullscreen');
    });



});







/*document.addEventListener('DOMContentLoaded', function() {


    const runButton = document.getElementById('run-button');
    const viewConsole = document.getElementById('view-console');
    const codeTextarea = document.getElementById('editor');
    const outputDiv = document.getElementById('output');
    let isEditorHidden = false;
*//*

    runButton.addEventListener('click', () => {
        if (!isEditorHidden) {


                    // get the code from code editor
                    var code = codeTextarea.getValue();
                    console.log(code); // This will log the code value to the console;



                   // Simulate running the code (replace with actual code execution logic)
                   // const result = executeCode(code);

                    // Hide the code editor and display the output
                    codeTextarea.style.display = 'none';
                    isEditorHidden = true;

                    // Display the output
                    //outputDiv.textContent = result;
                    outputDiv.style.display = 'block';
        }

    });

*//*

    function executeCode(code) {
        // Replace this with actual code execution logic (e.g., using an online compiler or interpreter)
        return 'Hello, World!';
    }


    viewConsole.addEventListener('click', () => {
        if (isEditorHidden) {
            // Show the code editor
            codeTextarea.style.display = 'block';
            outputDiv.style.display = 'none';
            isEditorHidden = false;
        } else {
            // Hide the code editor and display the output
            codeTextarea.style.display = 'none';
            isEditorHidden = true;

            // Simulate running the code (replace with actual code execution logic)
    *//*        const code = codeTextarea.value;
            const result = executeCode(code);*//*

            // Display the output
            *//*outputDiv.textContent = result;*//*
            outputDiv.style.display = 'block';
        }
    });

    *//*runButton.addEventListener('click', () => {
        if (isEditorHidden) {
            // Show the code editor
            codeTextarea.style.display = 'block';
            outputDiv.style.display = 'none';
            isEditorHidden = false;
        } else {
            // Hide the code editor and display the output
            codeTextarea.style.display = 'none';
            isEditorHidden = true;

            // Simulate running the code (replace with actual code execution logic)
            const code = codeTextarea.value;
            const result = executeCode(code);

            // Display the output
            outputDiv.textContent = result;
            outputDiv.style.display = 'block';
        }
    });*//*

});*/
