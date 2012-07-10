# clojure-ttt

Console based tic tac toe written in clojure with the aid of Leinighen, Speclj, and vim.

## Usage

You'll need to have both leinighen and clojure to run the application. Luckily, both of these can be obtained through homebrew on osx. 
* brew install clojure
* brew install leininghen

To run the game issue the command
* lein trampoline run

Using simply lein run will cause the game to become unresponsive due to a quirk in how lein swallows stdin.


To run the tests, simply use this call.
*lein spec


## License

Copyright (C) 2012 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
