function service(request, response) {
    response.writeHead(200, {'Content-Type': 'text/plain'});
    response.end('Hello Node.js');
}
function sayHello(){
	console.log('hello from how2j.js');
}
exports.hi = sayHello;
exports.service = service;