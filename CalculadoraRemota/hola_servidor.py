import sys
sys.path.append("gen-py")
from hola import ServicioCalculadora

from thrift.transport import TSocket	# Endpoint -> conectar clientes
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol # Serialización
from thrift.server import TServer # clases de servidores

# Implementación del servicio

class Calculadora:   # Manejador de llamadas al sevicio
	# Todos los métodos del servicio tienen que estar en el hanlder
	def hola_func(self):  # Método del servidor
		print("[Servidor] Atendiendo la solicitud del cliente")
		return "Hola desde el Servidor Apache Thrift en Python"

	def suma(self, numero1, numero2):
		return numero1 + numero2

	def multiplicar(self, numero1, numero2):
		return numero1 * numero2

	def restar(self, numero1, numero2):
		return numero1 - numero2

	def dividir(self, numero1, numero2):
		if numero2 == 0:
			return 0
		return numero1 / numero2


# Crear una instancia del manejador
manejador = Calculadora()

# Iniciacilizar un procesador para el servicio
procesador = ServicioCalculadora.Processor(manejador)

# Crear el socket TCP
puerto = 9090
transporte_serv = TSocket.TServerSocket(port=puerto)

# Buffer -> Eficiencia
transporte_fact = TTransport.TBufferedTransportFactory()

# Protocolo binario
protocolo_fact = TBinaryProtocol.TBinaryProtocolFactory()

# crear un servidor con funcionalidad básica
servidor = TServer.TSimpleServer(procesador, transporte_serv, transporte_fact, protocolo_fact)
print("Iniciando el servidor en el puerto %s" % puerto)
servidor.serve()
