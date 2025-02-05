need a java development kit to run
they need to be compiled aswell as being in the same directory as the files
% is to signify what the command is 
%javac finalClient.java
%javac Server.java

to run server must be ran before clients 

%java Server

for the client you need to pass in the servers ip address as an arguemnt
to get the ip it can be found using HostName -I on linux or ipconfig on windows

%java finalClient [server ip]