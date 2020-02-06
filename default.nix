let
  pkgs = import <nixpkgs> {
    overlays = [ (self: super: { jdk = super.openjdk11; }) ];
  };
in with pkgs;
stdenv.mkDerivation {
  name = "biblioteket";
  buildInputs = [ openjdk11 maven tiled ];

  APPEND_LIBRARY_PATH = stdenv.lib.makeLibraryPath [
    pkgs.xorg.libXcursor
    pkgs.xorg.libXi
    pkgs.xorg.libXrandr
    pkgs.xorg.libXxf86vm
    pkgs.openal
    pkgs.libGL
  ];
  shellHook = ''
    export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:$APPEND_LIBRARY_PATH"
  '';

}
