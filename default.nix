let
  pkgs = import <nixpkgs> {
    overlays = [
      (
        self: super: {
          jdk = super.openjdk11;
        }
      )
    ];
  };
in
pkgs.stdenv.mkDerivation {
  name = "biblioteket";
  buildInputs = with pkgs; [
    openjdk11
    maven
  ];
}
