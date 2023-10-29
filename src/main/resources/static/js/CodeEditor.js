$(document).ready(function(){
     var editor = CodeMirror.fromTextArea(codeEditor, {
       lineNumbers: true,
       mode:"xml",
       theme: "dracula",
       autoCloseTags: true,
     });
});