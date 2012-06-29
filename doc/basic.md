Layers
======

Context
-------

Methods:

  * paint
  * get room
  * ...

Listenable:

  * paint

Collab protocol
---------------

Methods:

  * join room
  * ...

Listenable:

  * connected to room
  * ...

Network protocol
----------------

Methods:

  * sendMessage (message object)

Listenable:

  * messageReceived (message object)

Physical
--------

Methods:

  * get server address
  * get server port
  * isConnected
  * connect (server address, port)
  * disconnect
  * send (array of bytes)

Listenable:

  * connection established
  * connection refused
  * connection closed
  * data received (array of bytes)


Data
======
