
//require('./package.json');

//require('jsdom');

//console.log(global)

//require("xmlhttprequest")

//var __ScalaJSEnv = { exportsNamespace: exports };

global.x= 1;

require('.')
require('./main/target/scala-2.11/scala-node-main-jsdeps.js')
require('./main/target/scala-2.11/scala-node-main-fastopt.js')


example.main2()
