# Nix Dev Shell

One command to get every dependency — no manual installs.

## Install Nix

If you don't have Nix installed:

### Linux and macos
```bash
curl --proto '=https' --tlsv1.2 -sSf -L https://nixos.org/nix/install | sh -s -- --daemon
```


### Windows (WSL2)
Install Nix inside your WSL2 distribution using the Linux command above. No native Windows support — use WSL2.



After install, restart your termenal

## Enter the Shell

From the project root:

```bash
cd shell
nix develop
```

This drops you into a shell with everything ready:
- ☕ Java 21 + Maven


## Build & Run

```bash
mvn compile #to compile code
mvn exec:java #run code

```
