
const my_port = process.argv[2]
const my_address = process.argv[3]
const destiny_port = process.argv[4]
const destiny_address = process.argv[5]


const dgram = require('dgram');
const server = dgram.createSocket('udp4');



server.on('error', (err) => {
    console.log(`server error:\n${err.stack}`);
    server.close();
});

server.on('message', (msg, rinfo) => {
    console.log(`mensagem: ${msg} de ${rinfo.address}:${rinfo.port}`);
});

server.on('listening', () => {
    const address = server.address();
    console.log(`server listening ${address.address}:${address.port}`);
});

server.bind({
    address: my_address,
    port: my_port,
});

const sendMensage = (msgn) => {
    const message = Buffer.from(msgn);
    console.log(`you sent: [${message}]`)
    server.send(message, destiny_port, destiny_address, (err) => {
    });
}   


process.openStdin().addListener("data", function (d) {
    if(d.toString().trim() == 'exit'){
        return process.exit()
    }
    // note:  d is an object, and when converted to a string it will
    // end with a linefeed.  so we (rather crudely) account for that  
    // with toString() and then trim() 
    sendMensage(d.toString().trim())
});