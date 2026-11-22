{ 
  description = "ics202";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-25.11";
  };

  outputs = { self, nixpkgs }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
    in
    {
      devShells.${system}.default = pkgs.mkShell {
        # 1. Tools you want to use
        buildInputs = [
          pkgs.figlet    # must keep this!
          pkgs.openjdk21
          pkgs.maven
        ];

        # 2. Tools needed to compile things (if any)
        # 3. Your variables (becomes 'export MY_API_KEY=...')
        #MY_API_KEY = "secret_stuff";

        # 4. Commands that run when you enter
        shellHook = ''
          clear   
          BLUE='\033[0;34m'
          CYAN='\033[0;36m'
          NC='\033[0m' # No Color (Resets the terminal color)

          echo -e "$BLUE"
          
          figlet "ICS 202"

          echo -e "$CYAN hello $(whoami)...                   $NC"
          echo -e "$BLUE ──────────────────────────────────────────────────────"
          echo ""
          echo ""
          echo -e "$BLUE loaded the following packages:   $NC"
          for pkg in $buildInputs; do
              name=$(echo $pkg | cut -d- -f2-)
              echo -e " -$name"
          done
          echo ""
          echo ""
          echo -e "$BLUE ──────────────────────────────────────────────────────"
          echo -e  "       made by: 𝔐𝔞𝔥𝔢𝔯 𝔇𝔞𝔤𝔢𝔰𝔱𝔞𝔫𝔦 and mansour mansour   $NC"
          echo "_____________________________________________________"
          echo -e " use: $BLUE mvn compile $NC to compile code "
	  echo -e  "and use $BLUE mvn exec:java $NC to run code "
        '';
      };

      
    };
}
