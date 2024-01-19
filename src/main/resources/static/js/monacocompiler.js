/*require.config({ paths: { 'vs': 'https://cdn.jsdelivr.net/npm/monaco-editor/min/vs' } });

require(['vs/editor/editor.main'], function () {
    var editor = monaco.editor.create(document.getElementById('editor'), {
        value: 'function hello() {\n\tconsole.log("Hello, world!");\n}',
        language: 'javascript',
        theme: 'vs-dark' // Replace with the theme name you want to use
    });
});*/


/*require.config({ paths: { 'vs': 'https://cdn.jsdelivr.net/npm/monaco-editor/min/vs' } });

require(['vs/editor/editor.main'], function () {
    var editor = monaco.editor.create(document.getElementById('editor'), {
        value: 'public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println("Hello, World!");\n    }\n}',
        language: 'java',
        theme: 'vs-dark' // Replace with the theme name you want to use
    });
});*/


        /*require.config({
            paths: { 'vs': 'https://cdn.jsdelivr.net/npm/monaco-editor/min/vs' }
        });

        require(['vs/editor/editor.main'], function () {
            monaco.languages.register({ id: 'java' });

            monaco.languages.setMonarchTokensProvider('java', {
                tokenizer: {
                    root: [
                        [/\b(class|public|static|void|int|String|System\.out\.println)\b/, "keyword"],
                        [/\b(\w+)\b/, "identifier"]
                    ]
                }
            });

            monaco.languages.registerCompletionItemProvider('java', {
                provideCompletionItems: function (model, position) {
                    var word = model.getWordUntilPosition(position);
                    return {
                        suggestions: [
                            {
                                label: 'System.out.println',
                                kind: monaco.languages.CompletionItemKind.Method,
                                insertText: 'System.out.println();',
                                range: word.range
                            },
                            {
                                label: 'int',
                                kind: monaco.languages.CompletionItemKind.Keyword,
                                insertText: 'int',
                                range: word.range
                            },
                            {
                                label: 'String',
                                kind: monaco.languages.CompletionItemKind.Keyword,
                                insertText: 'String',
                                range: word.range
                            }
                        ]
                    };
                }
            });

            monaco.editor.create(document.getElementById('editor'), {
                value: 'public class HelloWorld {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println("Hello, World");\n\t}\n}',
                language: 'java',
                theme: 'vs-dark'
            });

        });*/



        /*require.config({
            paths: { 'vs': 'https://cdn.jsdelivr.net/npm/monaco-editor/min/vs' }
        });

        require(['vs/editor/editor.main'], function () {
            monaco.languages.register({ id: 'java' });

            monaco.languages.setMonarchTokensProvider('java', {
                tokenizer: {
                    root: [
                        [/\b(class|public|static|void|int|String|System\.out\.println)\b/, "keyword"],
                        [/\b(\w+)\b/, "identifier"]
                    ]
                }
            });

            var editor = monaco.editor.create(document.getElementById('editor'), {
                value: 'public class HelloWorld {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println("Hello, World");\n\t}\n}',
                language: 'java',
                theme: 'vs-dark'
            });

            // Simulated error detection function
            function detectErrors() {
                const code = editor.getValue();
                const errorLines = [3]; // Simulated error on line 3

                const decorations = errorLines.map(lineNumber => ({
                    range: new monaco.Range(lineNumber, 1, lineNumber, 1),
                    options: {
                        isWholeLine: true,
                        linesDecorationsClassName: 'error-line'
                    }
                }));

                editor.deltaDecorations([], decorations);
            }

            detectErrors();
        });*/



        /*require.config({
                    paths: { 'vs': 'https://cdn.jsdelivr.net/npm/monaco-editor/min/vs' }
                });

                require(['vs/editor/editor.main'], function () {
                    var editor = monaco.editor.create(document.getElementById('editor'), {
                        value: 'console.log("Hello, world!");',
                        language: 'javascript',
                        theme: 'vs-dark'
                    });

                    // Load ESLint
                    var eslint = new ESLint({ fix: true });

                    // Function to lint and update the code
                    function lintCode() {
                        const code = editor.getValue();
                        eslint.lintText(code, {}).then((results) => {
                            const errors = results[0].messages;
                            const markers = errors.map((error) => {
                                return {
                                    startLineNumber: error.line,
                                    startColumn: error.column,
                                    endLineNumber: error.endLine,
                                    endColumn: error.endColumn,
                                    message: error.message,
                                    severity: monaco.MarkerSeverity.Error
                                };
                            });

                            monaco.editor.setModelMarkers(editor.getModel(), 'eslint', markers);
                        });
                    }

                    // Lint the code on changes
                    editor.onDidChangeModelContent(lintCode);
                    lintCode();
                });*/




 /*require.config({
             paths: { 'vs': 'https://cdn.jsdelivr.net/npm/monaco-editor/min/vs' }
         });

         require(['vs/editor/editor.main'], function () {
             var editor = monaco.editor.create(document.getElementById('editor'), {
                 value: 'public class HelloWorld {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println("Hello, World");\n\t}\n}',
                 language: 'java',
                 theme: 'vs-dark'
             });

             // Simulated Checkstyle analysis
             function analyzeJavaCode(code) {
                 // Simulate sending the code to Checkstyle for analysis
                 // Process the results and display them in the editor
                 // You would need to adapt this to work with a real Checkstyle integration
                 const results = [
                     { line: 3, column: 6, message: 'Indentation should be 4 spaces', severity: 'error' }
                 ];

                 const markers = results.map(result => ({
                     startLineNumber: result.line,
                     startColumn: result.column,
                     endLineNumber: result.line,
                     endColumn: result.column,
                     message: result.message,
                     severity: monaco.MarkerSeverity.Error
                 }));

                 monaco.editor.setModelMarkers(editor.getModel(), 'checkstyle', markers);
             }

             // Analyze the Java code in the editor
             analyzeJavaCode(editor.getValue());
         });*/



          require.config({
              paths: { 'vs': 'https://cdn.jsdelivr.net/npm/monaco-editor/min/vs' }
          });

          require(['vs/editor/editor.main'], function () {
              var editor = monaco.editor.create(document.getElementById('editor'), {
                  value: 'public class HelloWorld {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println("Hello, World");\n\t}\n}',
                  language: 'java',
                  theme: 'vs-dark'
              });

              function simulateCheckstyleAnalysis(javaCode) {
                  const results = [];

                  // Define your custom rule here
                  // For example, let's check for the presence of a specific method call
                  const forbiddenPattern = 'nit';

                  const lines = javaCode.split('\n');
                  for (let i = 0; i < lines.length; i++) {
                      if (lines[i].includes(forbiddenPattern)) {
                          results.push({
                              line: i + 1, // Line numbers are 1-based in Monaco Editor
                              column: lines[i].indexOf(forbiddenPattern) + 1,
                              message: `Avoid using '${forbiddenPattern}'`,
                              severity: 'error'
                          });
                      }
                  }

                  return results;
              }


              function analyzeJavaCode() {
                  const javaCode = editor.getValue();
                  const results = simulateCheckstyleAnalysis(javaCode);
                  const markers = results.map(result => ({
                      startLineNumber: result.line,
                      startColumn: result.column,
                      endLineNumber: result.line,
                      endColumn: result.column,
                      message: result.message,
                      severity: monaco.MarkerSeverity.Error
                  }));
                  monaco.editor.setModelMarkers(editor.getModel(), 'checkstyle', markers);
              }

              // Analyze the Java code in the editor
              analyzeJavaCode();

              // Listen for changes in the code and re-analyze
              editor.onDidChangeModelContent(analyzeJavaCode);


            const runButton = document.getElementById('run-button');
            const viewConsole = document.getElementById('view-console');
            const codeTextarea = document.getElementById('editor');
            const outputDiv = document.getElementById('output');
            let isEditorHidden = false;

                  runButton.addEventListener('click', () => {
                      if (!isEditorHidden) {


                                  // get the code from code editor
                                  var code = editor.getValue();
                                  console.log(code); // This will log the code value to the console;



                                 // Simulate running the code (replace with actual code execution logic)
                                // const result = executeCode(code);

                                // now call the judge0 api with data
                                /*const url = 'https://judge0-ce.p.rapidapi.com/languages';
                                const options = {
                                    method: 'GET',
                                    headers: {
                                    		'X-RapidAPI-Key': '198c9e2812msh30c3c084c55a9d1p149b3bjsnfcd1a78f88e0',
                                    		'X-RapidAPI-Host': 'judge0-ce.p.rapidapi.com'
                                    	}
                                };

                                async function fetchData() {
                                    try {
                                        const response = await fetch(url, options);
                                        if (response.ok) {
                                            const result = await response.text();
                                            console.log(result);
                                             // Hide the code editor and display the output
                                             codeTextarea.style.display = 'none';
                                             isEditorHidden = true;

                                             // Display the output
                                             outputDiv.textContent = result;
                                             outputDiv.style.display = 'block';

                                        } else {
                                            console.error('Request failed with status: ' + response.status);
                                        }
                                    } catch (error) {
                                        console.error(error);
                                    }
                                }

                                fetchData();*/

                                // Hide the code editor and display the output
                                codeTextarea.style.display = 'none';
                                isEditorHidden = true;

                                // Display the output
                                outputDiv.textContent = result;
                                outputDiv.style.display = 'block';
                      }

                  });



                  function executeCode(code) {
                      // Replace this with actual code execution logic (e.g., using an online compiler or interpreter)

                      return result;
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
                  /*      const code = codeTextarea.value;
                          const result = executeCode(code);*/

                          // Display the output
                          /*outputDiv.textContent = result;*/
                          outputDiv.style.display = 'block';
                      }
                  });

                  /*runButton.addEventListener('click', () => {
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
                  });*/



          });
