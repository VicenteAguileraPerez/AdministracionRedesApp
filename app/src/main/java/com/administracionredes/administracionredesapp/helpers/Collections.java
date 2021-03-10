package com.administracionredes.administracionredesapp.helpers;

import androidx.annotation.NonNull;

public enum Collections
{
    FALLAS {
        @NonNull
        @Override
        public String toString() {
            return "Fallas";
        }
    },
    LOCALIZACION {
        @NonNull
        @Override
        public String toString() {
            return "Localizacion";
        }

    },
    INVENTARIO {
        @NonNull
        @Override
        public String toString() {
            return "Inventario";
        }

    },
    CONFIGURACION {
        @NonNull
        @Override
        public String toString() {
            return "Configuracion";
        }

    };

}
