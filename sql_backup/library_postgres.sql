PGDMP     &        
             |            library    15.5    15.5 X    :           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ;           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            <           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            =           1262    16401    library    DATABASE     �   CREATE DATABASE library WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE library;
                postgres    false            >           0    0    DATABASE library    COMMENT     �   COMMENT ON DATABASE library IS 'Esquema de base de datos creado para registrar las entidades e informacion de la aplicacion Qlibrary';
                   postgres    false    3389                        2615    16402    library    SCHEMA        CREATE SCHEMA library;
    DROP SCHEMA library;
                postgres    false            ?           0    0    SCHEMA library    COMMENT     /   COMMENT ON SCHEMA library IS 'Schema library';
                   postgres    false    5            �            1259    16425    appuser_id_seq    SEQUENCE     x   CREATE SEQUENCE library.appuser_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE library.appuser_id_seq;
       library          library    false    5            �            1259    16404    appuser    TABLE     �  CREATE TABLE library.appuser (
    id bigint DEFAULT nextval('library.appuser_id_seq'::regclass) NOT NULL,
    name character varying(100) NOT NULL,
    access_code character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    last_entry_date date,
    firstname character varying,
    lastname character varying,
    identification_code character varying,
    creation_date timestamp without time zone
);
    DROP TABLE library.appuser;
       library         heap    library    false    218    5            @           0    0    TABLE appuser    COMMENT     X   COMMENT ON TABLE library.appuser IS 'Entidad para representar un usuario del sistema.';
          library          library    false    214            A           0    0    COLUMN appuser.id    COMMENT     Q   COMMENT ON COLUMN library.appuser.id IS 'identificador unico de base de datos.';
          library          library    false    214            B           0    0    COLUMN appuser.name    COMMENT     L   COMMENT ON COLUMN library.appuser.name IS 'Nombre de usuario del sistema.';
          library          library    false    214            C           0    0    COLUMN appuser.access_code    COMMENT     w   COMMENT ON COLUMN library.appuser.access_code IS 'Codigo de acceso utilizado por el usuario para acceder al sistema.';
          library          library    false    214            D           0    0    COLUMN appuser.email    COMMENT     N   COMMENT ON COLUMN library.appuser.email IS 'Correo electronico del usuario.';
          library          library    false    214            E           0    0    COLUMN appuser.last_entry_date    COMMENT     r   COMMENT ON COLUMN library.appuser.last_entry_date IS 'Fecha de registro de actividad del usuario en el sistema.';
          library          library    false    214            F           0    0    COLUMN appuser.firstname    COMMENT     K   COMMENT ON COLUMN library.appuser.firstname IS 'Nombre real del usuario.';
          library          library    false    214            G           0    0    COLUMN appuser.lastname    COMMENT     J   COMMENT ON COLUMN library.appuser.lastname IS 'Apellido(s) del usuario.';
          library          library    false    214            H           0    0 "   COLUMN appuser.identification_code    COMMENT     m   COMMENT ON COLUMN library.appuser.identification_code IS 'Codigo de identificacion unico del usuario (DNI)';
          library          library    false    214            I           0    0    COLUMN appuser.creation_date    COMMENT     ^   COMMENT ON COLUMN library.appuser.creation_date IS 'Momento exacto de creacion del usuario.';
          library          library    false    214            �            1259    16495    appuser_role_aux    TABLE     g   CREATE TABLE library.appuser_role_aux (
    appuser_id bigint NOT NULL,
    role_id bigint NOT NULL
);
 %   DROP TABLE library.appuser_role_aux;
       library         heap    library    false    5            J           0    0    TABLE appuser_role_aux    COMMENT     p   COMMENT ON TABLE library.appuser_role_aux IS 'tabla que relaciona al usuario y rol que existen en el sistema.';
          library          library    false    225            K           0    0 "   COLUMN appuser_role_aux.appuser_id    COMMENT     i   COMMENT ON COLUMN library.appuser_role_aux.appuser_id IS 'Identificador asociado a la entidad usuario.';
          library          library    false    225            �            1259    16427    author_id_seq    SEQUENCE     w   CREATE SEQUENCE library.author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE library.author_id_seq;
       library          library    false    5            �            1259    16416    author    TABLE     T  CREATE TABLE library.author (
    id bigint DEFAULT nextval('library.author_id_seq'::regclass) NOT NULL,
    name character varying NOT NULL,
    lastname character varying,
    origin_country character varying,
    birthday date,
    code character varying,
    appuser_id bigint NOT NULL,
    creation_date timestamp without time zone
);
    DROP TABLE library.author;
       library         heap    library    false    219    5            L           0    0    TABLE author    COMMENT     U   COMMENT ON TABLE library.author IS 'Entidad para representar el autor de un libro.';
          library          library    false    215            M           0    0    COLUMN author.id    COMMENT     b   COMMENT ON COLUMN library.author.id IS 'Identificador unico de autor de un libro en el sistema.';
          library          library    false    215            N           0    0    COLUMN author.name    COMMENT     H   COMMENT ON COLUMN library.author.name IS 'Nombre del autor del libro.';
          library          library    false    215            O           0    0    COLUMN author.lastname    COMMENT     Q   COMMENT ON COLUMN library.author.lastname IS 'Apellido(s) del autor del libro.';
          library          library    false    215            P           0    0    COLUMN author.origin_country    COMMENT     P   COMMENT ON COLUMN library.author.origin_country IS 'Pais de origen del autor.';
          library          library    false    215            Q           0    0    COLUMN author.birthday    COMMENT     O   COMMENT ON COLUMN library.author.birthday IS 'Fecha de nacimiento del autor.';
          library          library    false    215            R           0    0    COLUMN author.code    COMMENT     Q   COMMENT ON COLUMN library.author.code IS 'Codigo Unico del autor autogenerado.';
          library          library    false    215            S           0    0    COLUMN author.appuser_id    COMMENT     o   COMMENT ON COLUMN library.author.appuser_id IS 'Identificador del usuario que realizo la creacion del autor.';
          library          library    false    215            T           0    0    COLUMN author.creation_date    COMMENT     `   COMMENT ON COLUMN library.author.creation_date IS 'Momento exacto de la creacion del usuario.';
          library          library    false    215            �            1259    16452    book_id_seq    SEQUENCE     u   CREATE SEQUENCE library.book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE library.book_id_seq;
       library          library    false    5            �            1259    16419    book    TABLE     +  CREATE TABLE library.book (
    id bigint DEFAULT nextval('library.book_id_seq'::regclass) NOT NULL,
    title character varying,
    publisher character varying,
    code character varying,
    appuser_id bigint NOT NULL,
    creation_date timestamp without time zone,
    publication_date date
);
    DROP TABLE library.book;
       library         heap    library    false    220    5            U           0    0 
   TABLE book    COMMENT     Z   COMMENT ON TABLE library.book IS 'Entidad para representar un libro dentro del sistema.';
          library          library    false    216            V           0    0    COLUMN book.id    COMMENT     N   COMMENT ON COLUMN library.book.id IS 'Identificador unico de base de datos.';
          library          library    false    216            W           0    0    COLUMN book.title    COMMENT     =   COMMENT ON COLUMN library.book.title IS 'Titulo del libro.';
          library          library    false    216            X           0    0    COLUMN book.publisher    COMMENT     i   COMMENT ON COLUMN library.book.publisher IS 'Empresa que realiza la impresion o publicacion del libro.';
          library          library    false    216            Y           0    0    COLUMN book.code    COMMENT     U   COMMENT ON COLUMN library.book.code IS 'Codigo asociado al libro en la biblioteca.';
          library          library    false    216            Z           0    0    COLUMN book.appuser_id    COMMENT     m   COMMENT ON COLUMN library.book.appuser_id IS 'Identificador del usuario que realiza la creacion del libro.';
          library          library    false    216            [           0    0    COLUMN book.creation_date    COMMENT     \   COMMENT ON COLUMN library.book.creation_date IS 'Momento exacto de creacion del registro.';
          library          library    false    216            \           0    0    COLUMN book.publication_date    COMMENT     k   COMMENT ON COLUMN library.book.publication_date IS 'Fecha de publicacion del libro en formato AAAA-MM-DD';
          library          library    false    216            �            1259    16460    book_author_aux    TABLE     [   CREATE TABLE library.book_author_aux (
    book_id bigint NOT NULL,
    autor_id bigint
);
 $   DROP TABLE library.book_author_aux;
       library         heap    library    false    5            ]           0    0    COLUMN book_author_aux.book_id    COMMENT     Y   COMMENT ON COLUMN library.book_author_aux.book_id IS 'Identificador asociado al libro.';
          library          library    false    222            ^           0    0    COLUMN book_author_aux.autor_id    COMMENT     e   COMMENT ON COLUMN library.book_author_aux.autor_id IS 'Identificador asoaciado al Autor del libro.';
          library          library    false    222            �            1259    16473    book_category_aux    TABLE     `   CREATE TABLE library.book_category_aux (
    book_id bigint NOT NULL,
    category_id bigint
);
 &   DROP TABLE library.book_category_aux;
       library         heap    library    false    5            _           0    0    TABLE book_category_aux    COMMENT     r   COMMENT ON TABLE library.book_category_aux IS 'Tabla que relaciona a la entidad Libro con la entidad Categoria.';
          library          library    false    223            `           0    0     COLUMN book_category_aux.book_id    COMMENT     d   COMMENT ON COLUMN library.book_category_aux.book_id IS 'Identificador asociado a la entidad Libro';
          library          library    false    223            a           0    0 $   COLUMN book_category_aux.category_id    COMMENT     l   COMMENT ON COLUMN library.book_category_aux.category_id IS 'Identificador asociado a la entidad Categoria';
          library          library    false    223            �            1259    16458    category_id_seq    SEQUENCE     y   CREATE SEQUENCE library.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE library.category_id_seq;
       library          library    false    5            �            1259    16422    category    TABLE     �   CREATE TABLE library.category (
    id bigint DEFAULT nextval('library.category_id_seq'::regclass) NOT NULL,
    name character varying
);
    DROP TABLE library.category;
       library         heap    library    false    221    5            b           0    0    TABLE category    COMMENT     n   COMMENT ON TABLE library.category IS 'Entidad para representar la categoria de un libro dentro del sistema.';
          library          library    false    217            c           0    0    COLUMN category.id    COMMENT     U   COMMENT ON COLUMN library.category.id IS 'Identificador unico de la base de datos.';
          library          library    false    217            d           0    0    COLUMN category.name    COMMENT     P   COMMENT ON COLUMN library.category.name IS 'Nombre de la categoria del libro.';
          library          library    false    217            �            1259    16508    role_id_seq    SEQUENCE     u   CREATE SEQUENCE library.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE library.role_id_seq;
       library          library    false    5            �            1259    16488    role    TABLE     �   CREATE TABLE library.role (
    id bigint DEFAULT nextval('library.role_id_seq'::regclass) NOT NULL,
    name character varying,
    description character varying
);
    DROP TABLE library.role;
       library         heap    library    false    226    5            e           0    0 
   TABLE role    COMMENT     w   COMMENT ON TABLE library.role IS 'Entidad diseñada para identificar los roles del sistema basado en Spring Security';
          library          library    false    224            f           0    0    COLUMN role.id    COMMENT     E   COMMENT ON COLUMN library.role.id IS 'Identificador unico del rol.';
          library          library    false    224            g           0    0    COLUMN role.name    COMMENT     T   COMMENT ON COLUMN library.role.name IS 'Nombre del rol asosiado a Spring Security';
          library          library    false    224            h           0    0    COLUMN role.description    COMMENT     V   COMMENT ON COLUMN library.role.description IS 'Descripcion del rol para el sistema.';
          library          library    false    224            +          0    16404    appuser 
   TABLE DATA           �   COPY library.appuser (id, name, access_code, email, last_entry_date, firstname, lastname, identification_code, creation_date) FROM stdin;
    library          library    false    214   �[       6          0    16495    appuser_role_aux 
   TABLE DATA           @   COPY library.appuser_role_aux (appuser_id, role_id) FROM stdin;
    library          library    false    225   +\       ,          0    16416    author 
   TABLE DATA           p   COPY library.author (id, name, lastname, origin_country, birthday, code, appuser_id, creation_date) FROM stdin;
    library          library    false    215   L\       -          0    16419    book 
   TABLE DATA           h   COPY library.book (id, title, publisher, code, appuser_id, creation_date, publication_date) FROM stdin;
    library          library    false    216   i\       3          0    16460    book_author_aux 
   TABLE DATA           =   COPY library.book_author_aux (book_id, autor_id) FROM stdin;
    library          library    false    222   �\       4          0    16473    book_category_aux 
   TABLE DATA           B   COPY library.book_category_aux (book_id, category_id) FROM stdin;
    library          library    false    223   �\       .          0    16422    category 
   TABLE DATA           -   COPY library.category (id, name) FROM stdin;
    library          library    false    217   �\       5          0    16488    role 
   TABLE DATA           6   COPY library.role (id, name, description) FROM stdin;
    library          library    false    224    ]       i           0    0    appuser_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('library.appuser_id_seq', 1, true);
          library          library    false    218            j           0    0    author_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('library.author_id_seq', 1, false);
          library          library    false    219            k           0    0    book_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('library.book_id_seq', 1, false);
          library          library    false    220            l           0    0    category_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('library.category_id_seq', 7, true);
          library          library    false    221            m           0    0    role_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('library.role_id_seq', 2, true);
          library          library    false    226            �           2606    16410    appuser appuser_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY library.appuser
    ADD CONSTRAINT appuser_pkey PRIMARY KEY (id);
 ?   ALTER TABLE ONLY library.appuser DROP CONSTRAINT appuser_pkey;
       library            library    false    214            �           2606    16437    author author_pk 
   CONSTRAINT     O   ALTER TABLE ONLY library.author
    ADD CONSTRAINT author_pk PRIMARY KEY (id);
 ;   ALTER TABLE ONLY library.author DROP CONSTRAINT author_pk;
       library            library    false    215            �           2606    16446    book book_pk 
   CONSTRAINT     K   ALTER TABLE ONLY library.book
    ADD CONSTRAINT book_pk PRIMARY KEY (id);
 7   ALTER TABLE ONLY library.book DROP CONSTRAINT book_pk;
       library            library    false    216            �           2606    16457    category category_pk 
   CONSTRAINT     S   ALTER TABLE ONLY library.category
    ADD CONSTRAINT category_pk PRIMARY KEY (id);
 ?   ALTER TABLE ONLY library.category DROP CONSTRAINT category_pk;
       library            library    false    217            �           2606    16494    role role_pk 
   CONSTRAINT     K   ALTER TABLE ONLY library.role
    ADD CONSTRAINT role_pk PRIMARY KEY (id);
 7   ALTER TABLE ONLY library.role DROP CONSTRAINT role_pk;
       library            library    false    224            �           2606    16431    author appuser_fk    FK CONSTRAINT     o   ALTER TABLE ONLY library.author
    ADD CONSTRAINT appuser_fk FOREIGN KEY (id) REFERENCES library.appuser(id);
 <   ALTER TABLE ONLY library.author DROP CONSTRAINT appuser_fk;
       library          library    false    3212    215    214            �           2606    16447    book appuser_fk    FK CONSTRAINT     m   ALTER TABLE ONLY library.book
    ADD CONSTRAINT appuser_fk FOREIGN KEY (id) REFERENCES library.appuser(id);
 :   ALTER TABLE ONLY library.book DROP CONSTRAINT appuser_fk;
       library          library    false    216    3212    214            �           2606    16498 $   appuser_role_aux appuser_role_aux_fk    FK CONSTRAINT     �   ALTER TABLE ONLY library.appuser_role_aux
    ADD CONSTRAINT appuser_role_aux_fk FOREIGN KEY (appuser_id) REFERENCES library.appuser(id);
 O   ALTER TABLE ONLY library.appuser_role_aux DROP CONSTRAINT appuser_role_aux_fk;
       library          library    false    214    3212    225            �           2606    16503 &   appuser_role_aux appuser_role_aux_fk_1    FK CONSTRAINT     �   ALTER TABLE ONLY library.appuser_role_aux
    ADD CONSTRAINT appuser_role_aux_fk_1 FOREIGN KEY (role_id) REFERENCES library.role(id);
 Q   ALTER TABLE ONLY library.appuser_role_aux DROP CONSTRAINT appuser_role_aux_fk_1;
       library          library    false    224    225    3220            �           2606    16463 "   book_author_aux book_author_aux_fk    FK CONSTRAINT     �   ALTER TABLE ONLY library.book_author_aux
    ADD CONSTRAINT book_author_aux_fk FOREIGN KEY (book_id) REFERENCES library.book(id);
 M   ALTER TABLE ONLY library.book_author_aux DROP CONSTRAINT book_author_aux_fk;
       library          library    false    216    222    3216            �           2606    16468 $   book_author_aux book_author_aux_fk_1    FK CONSTRAINT     �   ALTER TABLE ONLY library.book_author_aux
    ADD CONSTRAINT book_author_aux_fk_1 FOREIGN KEY (autor_id) REFERENCES library.author(id);
 O   ALTER TABLE ONLY library.book_author_aux DROP CONSTRAINT book_author_aux_fk_1;
       library          library    false    222    3214    215            �           2606    16476 &   book_category_aux book_category_aux_fk    FK CONSTRAINT     �   ALTER TABLE ONLY library.book_category_aux
    ADD CONSTRAINT book_category_aux_fk FOREIGN KEY (book_id) REFERENCES library.book(id);
 Q   ALTER TABLE ONLY library.book_category_aux DROP CONSTRAINT book_category_aux_fk;
       library          library    false    216    3216    223            �           2606    16481 (   book_category_aux book_category_aux_fk_1    FK CONSTRAINT     �   ALTER TABLE ONLY library.book_category_aux
    ADD CONSTRAINT book_category_aux_fk_1 FOREIGN KEY (category_id) REFERENCES library.category(id);
 S   ALTER TABLE ONLY library.book_category_aux DROP CONSTRAINT book_category_aux_fk_1;
       library          library    false    223    217    3218                       826    16403    DEFAULT PRIVILEGES FOR TABLES    DEFAULT ACL     ^   ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA library GRANT ALL ON TABLES  TO library;
          library          postgres    false    5            +   �   x�3�LL����T1JT14P�JMK�Ot4/��v�O��72��+I�p)uKu��rL�r��s�,ʭ���ᐞ��������id`d�k`�kd5=��������L,�M,,�͸b���� ~(;      6      x�3�4����� ]      ,      x������ � �      -      x������ � �      3      x������ � �      4      x������ � �      .   P   x��;� 뷇1�_��K�5�V����$3�C�j-��2�y��t��ώZdB`�S��k��/d�}RYqX���ND^a�{      5   K   x�3���q�wt����tL����,.)JL�/RHI�Q(�Rs�� �|<���"9�2�r2�KR��2�b���� \��     