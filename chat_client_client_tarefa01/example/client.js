var dgram = require('dgram'),
	server = {
		host: 'localhost',
		port: 3000
	};

function Command() {

	process.stdin.on('data', function (chunk) {

		var message = chunk.toString().replace(/\n|\n/g, '');

		if (message === 'exit') {
			var object = '{"type":"disconnect"}';
			console.log('Pressione "Ctrl + C" para encerrar.');
		} else {
			var object = '{"type":"message","message":"' + message + '"}';
		}

		var buffer = Buffer.from(object);
		client.send(buffer, 0, buffer.length, server.port, server.host);

	});

}

var client = dgram.createSocket('udp4', function (message, rinfo) {

	console.log('%s', message.toString());
	process.stdin.resume();

	process.stdin.removeAllListeners('data');
	process.stdin.on('data', function (chunk) {
		Command();
	});

});

client.bind();

client.on('listening', function () {

	var buffer = Buffer.from('{"type":"connect"}');

	console.log('Cliente conectou-se a porta:.', client.address().port);
	console.log('(Digite "exit" para encerrar)');
	client.send(buffer, 0, buffer.length, server.port, server.host);

});

client.on('error', function (err) {
	console.log(err);
});

client.on('close', function () {

	var buffer = Buffer.from('{"type":"disconnect"}');

	console.log('Cliente desconectado.', client.address().port);
	client.send(buffer, 0, buffer.length, server.port, server.host);

})

process.stdin.resume();
Command();