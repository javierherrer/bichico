from PytrendsAddon import interest_by_city
from pytrends.request import TrendReq
import psycopg2
from psycopg2 import sql

HOST = "139.59.205.113"
PORT = "9999"
USER = "admin"
PASS = "123456"


def conexion():
    conn = psycopg2.connect(database="postgres",
                            user=USER,
                            password=PASS,
                            host=HOST,
                            port=PORT)
    return conn

def insertar_postgres(id_region, term_busqueda, interes):
    conn = conexion()
    cur = conn.cursor()
    cur.execute(
        "INSERT INTO Bichico.busqueda (id_region, term_busqueda, interes) values (%s, %s, %s)",
        (id_region, term_busqueda, interes))
    conn.commit()
    cur.execute("SELECT * FROM Bichico.busqueda")
    # print(cur.fetchall())

def obtener_id_region(nombreregion):
    query = sql.SQL("SELECT r.id_region FROM Bichico.region r WHERE r.nombre=%s")
    conn = conexion()
    cur = conn.cursor()
    cur.execute("SELECT r.id FROM Bichico.region r WHERE r.nombre=%s", (nombreregion,))
    # print(cur.fetchone()[0])
    return cur.fetchone()[0]

def obtener_terminos():
    terminos = []
    conn = conexion()
    cur = conn.cursor()
    cur.execute(
        "SELECT p.nombre FROM Bichico.palabra p"
    )
    for row in cur:
        terminos.append(row[0])
    # print(terminos)
    return terminos

def insertar_sintomas(sintomas):
    for sintoma in sintomas:
        for ciudad in sintomas[sintoma]:
            ciudad = ciudad
            try:
                id_ciudad = obtener_id_region(ciudad)
                interes = sintomas[sintoma][ciudad]
                insertar_postgres(id_ciudad, sintoma, interes)
            except TypeError:
                print("ERROR: " +  ciudad)

def obtener_diccionario_sintomas():
    tendencias = TrendReq(hl='es-ES', geo='ES')
    terminos = obtener_terminos()
    listica = list()
    i = 1
    for termino in terminos:
        listica.append(termino)
        if i % 5 == 0 or i == len(terminos):
            print(listica)
            tendencias.build_payload(kw_list=listica)
            resultados = interest_by_city(tendencias)
            insertar_sintomas(resultados.to_dict())
            listica.clear()
        # resultados = tendencias.interest_by_region(resolution='CITY', inc_low_vol=True, inc_geo_code=False)
        i = i + 1

def main():
    obtener_diccionario_sintomas()
    # print(sintomas)
    # insertar_sintomas(sintomas)

if __name__ == '__main__':
    main()
