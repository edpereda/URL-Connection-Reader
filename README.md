# URL-Connection-Reader
Programa realizado en lenguaje Java para la materia "Aplicaciones para Comunicaciones en Red" de la Escuela Superior de Cómputo (ESCOM) del Instituto Politécnico Nacional (IPN).

### Realizado en el año 2019. Lenguaje Java. IDE Netbeans.

## Descripción
El programa recibe como parámetro una URL, y debe descargar toda esa página WEB, además si encuentra algún otro link o redireccionamiento, también debe descargarlo de manera contigua, por lo que el programa nunca termina hasta que no se interrumpa.

## Objetivo
- El programa debe recibir como parámetro la URL de la página que se desea descargar.
- Se debe utilizar un pool de hilos para descargar de manera paralela las páginas WEB.
- Las páginas se almacenarán en una carpeta llamada "pagina_web".
