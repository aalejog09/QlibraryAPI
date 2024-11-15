PGDMP     6    +                 |            library    15.5    15.5 Z    <           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            =           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            >           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    16401    library    DATABASE     �   CREATE DATABASE library WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE library;
                postgres    false            @           0    0    DATABASE library    COMMENT     �   COMMENT ON DATABASE library IS 'Esquema de base de datos creado para registrar las entidades e informacion de la aplicacion Qlibrary';
                   postgres    false    3391                        2615    16510    library    SCHEMA        CREATE SCHEMA library;
    DROP SCHEMA library;
                postgres    false            A           0    0    SCHEMA library    COMMENT     /   COMMENT ON SCHEMA library IS 'Schema library';
                   postgres    false    5            �            1259    16511    appuser_id_seq    SEQUENCE     x   CREATE SEQUENCE library.appuser_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE library.appuser_id_seq;
       library          library    false    5            B           0    0    SEQUENCE appuser_id_seq    ACL     :   GRANT ALL ON SEQUENCE library.appuser_id_seq TO postgres;
          library          library    false    214            �            1259    16512    appuser    TABLE     �  CREATE TABLE library.appuser (
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
       library         heap    library    false    214    5            C           0    0    TABLE appuser    COMMENT     X   COMMENT ON TABLE library.appuser IS 'Entidad para representar un usuario del sistema.';
          library          library    false    215            D           0    0    COLUMN appuser.id    COMMENT     Q   COMMENT ON COLUMN library.appuser.id IS 'identificador unico de base de datos.';
          library          library    false    215            E           0    0    COLUMN appuser.name    COMMENT     L   COMMENT ON COLUMN library.appuser.name IS 'Nombre de usuario del sistema.';
          library          library    false    215            F           0    0    COLUMN appuser.access_code    COMMENT     w   COMMENT ON COLUMN library.appuser.access_code IS 'Codigo de acceso utilizado por el usuario para acceder al sistema.';
          library          library    false    215            G           0    0    COLUMN appuser.email    COMMENT     N   COMMENT ON COLUMN library.appuser.email IS 'Correo electronico del usuario.';
          library          library    false    215            H           0    0    COLUMN appuser.last_entry_date    COMMENT     r   COMMENT ON COLUMN library.appuser.last_entry_date IS 'Fecha de registro de actividad del usuario en el sistema.';
          library          library    false    215            I           0    0    COLUMN appuser.firstname    COMMENT     K   COMMENT ON COLUMN library.appuser.firstname IS 'Nombre real del usuario.';
          library          library    false    215            J           0    0    COLUMN appuser.lastname    COMMENT     J   COMMENT ON COLUMN library.appuser.lastname IS 'Apellido(s) del usuario.';
          library          library    false    215            K           0    0 "   COLUMN appuser.identification_code    COMMENT     m   COMMENT ON COLUMN library.appuser.identification_code IS 'Codigo de identificacion unico del usuario (DNI)';
          library          library    false    215            L           0    0    COLUMN appuser.creation_date    COMMENT     ^   COMMENT ON COLUMN library.appuser.creation_date IS 'Momento exacto de creacion del usuario.';
          library          library    false    215            �            1259    16518    appuser_role_aux    TABLE     g   CREATE TABLE library.appuser_role_aux (
    appuser_id bigint NOT NULL,
    role_id bigint NOT NULL
);
 %   DROP TABLE library.appuser_role_aux;
       library         heap    library    false    5            M           0    0    TABLE appuser_role_aux    COMMENT     p   COMMENT ON TABLE library.appuser_role_aux IS 'tabla que relaciona al usuario y rol que existen en el sistema.';
          library          library    false    216            N           0    0 "   COLUMN appuser_role_aux.appuser_id    COMMENT     i   COMMENT ON COLUMN library.appuser_role_aux.appuser_id IS 'Identificador asociado a la entidad usuario.';
          library          library    false    216            �            1259    16521    author_id_seq    SEQUENCE     w   CREATE SEQUENCE library.author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE library.author_id_seq;
       library          library    false    5            �            1259    16522    author    TABLE     T  CREATE TABLE library.author (
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
       library         heap    library    false    217    5            O           0    0    TABLE author    COMMENT     U   COMMENT ON TABLE library.author IS 'Entidad para representar el autor de un libro.';
          library          library    false    218            P           0    0    COLUMN author.id    COMMENT     b   COMMENT ON COLUMN library.author.id IS 'Identificador unico de autor de un libro en el sistema.';
          library          library    false    218            Q           0    0    COLUMN author.name    COMMENT     H   COMMENT ON COLUMN library.author.name IS 'Nombre del autor del libro.';
          library          library    false    218            R           0    0    COLUMN author.lastname    COMMENT     Q   COMMENT ON COLUMN library.author.lastname IS 'Apellido(s) del autor del libro.';
          library          library    false    218            S           0    0    COLUMN author.origin_country    COMMENT     P   COMMENT ON COLUMN library.author.origin_country IS 'Pais de origen del autor.';
          library          library    false    218            T           0    0    COLUMN author.birthday    COMMENT     O   COMMENT ON COLUMN library.author.birthday IS 'Fecha de nacimiento del autor.';
          library          library    false    218            U           0    0    COLUMN author.code    COMMENT     Q   COMMENT ON COLUMN library.author.code IS 'Codigo Unico del autor autogenerado.';
          library          library    false    218            V           0    0    COLUMN author.appuser_id    COMMENT     o   COMMENT ON COLUMN library.author.appuser_id IS 'Identificador del usuario que realizo la creacion del autor.';
          library          library    false    218            W           0    0    COLUMN author.creation_date    COMMENT     `   COMMENT ON COLUMN library.author.creation_date IS 'Momento exacto de la creacion del usuario.';
          library          library    false    218            �            1259    16528    book_id_seq    SEQUENCE     u   CREATE SEQUENCE library.book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE library.book_id_seq;
       library          library    false    5            �            1259    16529    book    TABLE     +  CREATE TABLE library.book (
    id bigint DEFAULT nextval('library.book_id_seq'::regclass) NOT NULL,
    title character varying,
    publisher character varying,
    code character varying,
    appuser_id bigint NOT NULL,
    creation_date timestamp without time zone,
    publication_date date
);
    DROP TABLE library.book;
       library         heap    library    false    219    5            X           0    0 
   TABLE book    COMMENT     Z   COMMENT ON TABLE library.book IS 'Entidad para representar un libro dentro del sistema.';
          library          library    false    220            Y           0    0    COLUMN book.id    COMMENT     N   COMMENT ON COLUMN library.book.id IS 'Identificador unico de base de datos.';
          library          library    false    220            Z           0    0    COLUMN book.title    COMMENT     =   COMMENT ON COLUMN library.book.title IS 'Titulo del libro.';
          library          library    false    220            [           0    0    COLUMN book.publisher    COMMENT     i   COMMENT ON COLUMN library.book.publisher IS 'Empresa que realiza la impresion o publicacion del libro.';
          library          library    false    220            \           0    0    COLUMN book.code    COMMENT     U   COMMENT ON COLUMN library.book.code IS 'Codigo asociado al libro en la biblioteca.';
          library          library    false    220            ]           0    0    COLUMN book.appuser_id    COMMENT     m   COMMENT ON COLUMN library.book.appuser_id IS 'Identificador del usuario que realiza la creacion del libro.';
          library          library    false    220            ^           0    0    COLUMN book.creation_date    COMMENT     \   COMMENT ON COLUMN library.book.creation_date IS 'Momento exacto de creacion del registro.';
          library          library    false    220            _           0    0    COLUMN book.publication_date    COMMENT     k   COMMENT ON COLUMN library.book.publication_date IS 'Fecha de publicacion del libro en formato AAAA-MM-DD';
          library          library    false    220            �            1259    16535    book_author_aux    TABLE     \   CREATE TABLE library.book_author_aux (
    book_id bigint NOT NULL,
    author_id bigint
);
 $   DROP TABLE library.book_author_aux;
       library         heap    library    false    5            `           0    0    COLUMN book_author_aux.book_id    COMMENT     Y   COMMENT ON COLUMN library.book_author_aux.book_id IS 'Identificador asociado al libro.';
          library          library    false    221            a           0    0     COLUMN book_author_aux.author_id    COMMENT     f   COMMENT ON COLUMN library.book_author_aux.author_id IS 'Identificador asoaciado al Autor del libro.';
          library          library    false    221            �            1259    16538    book_category_aux    TABLE     `   CREATE TABLE library.book_category_aux (
    book_id bigint NOT NULL,
    category_id bigint
);
 &   DROP TABLE library.book_category_aux;
       library         heap    library    false    5            b           0    0    TABLE book_category_aux    COMMENT     r   COMMENT ON TABLE library.book_category_aux IS 'Tabla que relaciona a la entidad Libro con la entidad Categoria.';
          library          library    false    222            c           0    0     COLUMN book_category_aux.book_id    COMMENT     d   COMMENT ON COLUMN library.book_category_aux.book_id IS 'Identificador asociado a la entidad Libro';
          library          library    false    222            d           0    0 $   COLUMN book_category_aux.category_id    COMMENT     l   COMMENT ON COLUMN library.book_category_aux.category_id IS 'Identificador asociado a la entidad Categoria';
          library          library    false    222            �            1259    16541    category_id_seq    SEQUENCE     y   CREATE SEQUENCE library.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE library.category_id_seq;
       library          library    false    5            �            1259    16542    category    TABLE     �   CREATE TABLE library.category (
    id bigint DEFAULT nextval('library.category_id_seq'::regclass) NOT NULL,
    name character varying
);
    DROP TABLE library.category;
       library         heap    library    false    223    5            e           0    0    TABLE category    COMMENT     n   COMMENT ON TABLE library.category IS 'Entidad para representar la categoria de un libro dentro del sistema.';
          library          library    false    224            f           0    0    COLUMN category.id    COMMENT     U   COMMENT ON COLUMN library.category.id IS 'Identificador unico de la base de datos.';
          library          library    false    224            g           0    0    COLUMN category.name    COMMENT     P   COMMENT ON COLUMN library.category.name IS 'Nombre de la categoria del libro.';
          library          library    false    224            �            1259    16548    role_id_seq    SEQUENCE     u   CREATE SEQUENCE library.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE library.role_id_seq;
       library          library    false    5            �            1259    16549    role    TABLE     �   CREATE TABLE library.role (
    id bigint DEFAULT nextval('library.role_id_seq'::regclass) NOT NULL,
    name character varying,
    description character varying
);
    DROP TABLE library.role;
       library         heap    library    false    225    5            h           0    0 
   TABLE role    COMMENT     w   COMMENT ON TABLE library.role IS 'Entidad diseñada para identificar los roles del sistema basado en Spring Security';
          library          library    false    226            i           0    0    COLUMN role.id    COMMENT     E   COMMENT ON COLUMN library.role.id IS 'Identificador unico del rol.';
          library          library    false    226            j           0    0    COLUMN role.name    COMMENT     T   COMMENT ON COLUMN library.role.name IS 'Nombre del rol asosiado a Spring Security';
          library          library    false    226            k           0    0    COLUMN role.description    COMMENT     V   COMMENT ON COLUMN library.role.description IS 'Descripcion del rol para el sistema.';
          library          library    false    226            .          0    16512    appuser 
   TABLE DATA           �   COPY library.appuser (id, name, access_code, email, last_entry_date, firstname, lastname, identification_code, creation_date) FROM stdin;
    library          library    false    215   �]       /          0    16518    appuser_role_aux 
   TABLE DATA           @   COPY library.appuser_role_aux (appuser_id, role_id) FROM stdin;
    library          library    false    216   _       1          0    16522    author 
   TABLE DATA           p   COPY library.author (id, name, lastname, origin_country, birthday, code, appuser_id, creation_date) FROM stdin;
    library          library    false    218   C_       3          0    16529    book 
   TABLE DATA           h   COPY library.book (id, title, publisher, code, appuser_id, creation_date, publication_date) FROM stdin;
    library          library    false    220   �`       4          0    16535    book_author_aux 
   TABLE DATA           >   COPY library.book_author_aux (book_id, author_id) FROM stdin;
    library          library    false    221   �a       5          0    16538    book_category_aux 
   TABLE DATA           B   COPY library.book_category_aux (book_id, category_id) FROM stdin;
    library          library    false    222   �a       7          0    16542    category 
   TABLE DATA           -   COPY library.category (id, name) FROM stdin;
    library          library    false    224   b       9          0    16549    role 
   TABLE DATA           6   COPY library.role (id, name, description) FROM stdin;
    library          library    false    226   zb       l           0    0    appuser_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('library.appuser_id_seq', 3, true);
          library          library    false    214            m           0    0    author_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('library.author_id_seq', 13, true);
          library          library    false    217            n           0    0    book_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('library.book_id_seq', 7, true);
          library          library    false    219            o           0    0    category_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('library.category_id_seq', 7, true);
          library          library    false    223            p           0    0    role_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('library.role_id_seq', 2, true);
          library          library    false    225            �           2606    16556    appuser appuser_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY library.appuser
    ADD CONSTRAINT appuser_pkey PRIMARY KEY (id);
 ?   ALTER TABLE ONLY library.appuser DROP CONSTRAINT appuser_pkey;
       library            library    false    215            �           2606    16558    author author_pk 
   CONSTRAINT     O   ALTER TABLE ONLY library.author
    ADD CONSTRAINT author_pk PRIMARY KEY (id);
 ;   ALTER TABLE ONLY library.author DROP CONSTRAINT author_pk;
       library            library    false    218            �           2606    16717    author author_validate 
   CONSTRAINT     v   ALTER TABLE ONLY library.author
    ADD CONSTRAINT author_validate UNIQUE (name, lastname, origin_country, birthday);
 A   ALTER TABLE ONLY library.author DROP CONSTRAINT author_validate;
       library            library    false    218    218    218    218            �           2606    16560    book book_pk 
   CONSTRAINT     K   ALTER TABLE ONLY library.book
    ADD CONSTRAINT book_pk PRIMARY KEY (id);
 7   ALTER TABLE ONLY library.book DROP CONSTRAINT book_pk;
       library            library    false    220            �           2606    16562    category category_pk 
   CONSTRAINT     S   ALTER TABLE ONLY library.category
    ADD CONSTRAINT category_pk PRIMARY KEY (id);
 ?   ALTER TABLE ONLY library.category DROP CONSTRAINT category_pk;
       library            library    false    224            �           2606    16564    role role_pk 
   CONSTRAINT     K   ALTER TABLE ONLY library.role
    ADD CONSTRAINT role_pk PRIMARY KEY (id);
 7   ALTER TABLE ONLY library.role DROP CONSTRAINT role_pk;
       library            library    false    226            �           2606    16575 $   appuser_role_aux appuser_role_aux_fk    FK CONSTRAINT     �   ALTER TABLE ONLY library.appuser_role_aux
    ADD CONSTRAINT appuser_role_aux_fk FOREIGN KEY (appuser_id) REFERENCES library.appuser(id);
 O   ALTER TABLE ONLY library.appuser_role_aux DROP CONSTRAINT appuser_role_aux_fk;
       library          library    false    3212    216    215            �           2606    16580 &   appuser_role_aux appuser_role_aux_fk_1    FK CONSTRAINT     �   ALTER TABLE ONLY library.appuser_role_aux
    ADD CONSTRAINT appuser_role_aux_fk_1 FOREIGN KEY (role_id) REFERENCES library.role(id);
 Q   ALTER TABLE ONLY library.appuser_role_aux DROP CONSTRAINT appuser_role_aux_fk_1;
       library          library    false    226    3222    216            �           2606    16729    author author_fk    FK CONSTRAINT     v   ALTER TABLE ONLY library.author
    ADD CONSTRAINT author_fk FOREIGN KEY (appuser_id) REFERENCES library.appuser(id);
 ;   ALTER TABLE ONLY library.author DROP CONSTRAINT author_fk;
       library          library    false    215    3212    218            �           2606    16585 "   book_author_aux book_author_aux_fk    FK CONSTRAINT     �   ALTER TABLE ONLY library.book_author_aux
    ADD CONSTRAINT book_author_aux_fk FOREIGN KEY (book_id) REFERENCES library.book(id);
 M   ALTER TABLE ONLY library.book_author_aux DROP CONSTRAINT book_author_aux_fk;
       library          library    false    221    220    3218            �           2606    16590 $   book_author_aux book_author_aux_fk_1    FK CONSTRAINT     �   ALTER TABLE ONLY library.book_author_aux
    ADD CONSTRAINT book_author_aux_fk_1 FOREIGN KEY (author_id) REFERENCES library.author(id);
 O   ALTER TABLE ONLY library.book_author_aux DROP CONSTRAINT book_author_aux_fk_1;
       library          library    false    3214    218    221            �           2606    16595 &   book_category_aux book_category_aux_fk    FK CONSTRAINT     �   ALTER TABLE ONLY library.book_category_aux
    ADD CONSTRAINT book_category_aux_fk FOREIGN KEY (book_id) REFERENCES library.book(id);
 Q   ALTER TABLE ONLY library.book_category_aux DROP CONSTRAINT book_category_aux_fk;
       library          library    false    3218    220    222            �           2606    16600 (   book_category_aux book_category_aux_fk_1    FK CONSTRAINT     �   ALTER TABLE ONLY library.book_category_aux
    ADD CONSTRAINT book_category_aux_fk_1 FOREIGN KEY (category_id) REFERENCES library.category(id);
 S   ALTER TABLE ONLY library.book_category_aux DROP CONSTRAINT book_category_aux_fk_1;
       library          library    false    222    224    3220            �           2606    16724    book book_fk    FK CONSTRAINT     r   ALTER TABLE ONLY library.book
    ADD CONSTRAINT book_fk FOREIGN KEY (appuser_id) REFERENCES library.appuser(id);
 7   ALTER TABLE ONLY library.book DROP CONSTRAINT book_fk;
       library          library    false    215    3212    220                       826    16605    DEFAULT PRIVILEGES FOR TABLES    DEFAULT ACL     ^   ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA library GRANT ALL ON TABLES  TO library;
          library          postgres    false    5            .   6  x�e��n�@���.؊3�8\V%��X+�q3�E�[Q(���Դ6=9�s����A��@�\DP��E�pc8���zٜ�����Ak�,4WW#Z3Ǜ��Ҽf����x�3��Ү� ���!�ctO�e	ԯyx� �!҉*�DU*`�i�������r��t��{�~fe��y\��t�xe�ofz��[UrP�Ǡ�ȓ]��D�������k��$����X�	S"Ȁ�4<�xR�؎Vk�ei�$F�K���)�h�m�q�$՚�������ctd���1��R�<��0�,+E�嶚D(6� ��z�      /      x�3�4�2�4�2�=... :      1   :  x�u��j�0��y�K�G�i��&%i�;��16����H�|�?������������c�16��\*.	�P!)�X!��G.J��W`���%*�Ч ���B'�w�a܄&kUU**��5�˅f=����7� I��CZuY�M�t�4��)���yee��аCw�oC��!4��r���љ"eX]D���7�QI�#X�b�q�\����HF�=.=�7Zֵ6�}w�\�]p��������e��:�>�S�����Ȇ���%;#���0������L�k��h{%�I�V�x�B�O��G      3     x���Mn� �5>�̎:TE�O�I�F�8]Yum)M�_�UZ�t��h���
S۲i��.T6��i��ާ#�L��!���XQ���5�:�D
�X���9M��{��օ3e����4��1���<��:����<�b6R#=�64�\4u�զ���xꇡ�e������ ,�򳗢g[��֖�-6/��y�����$5Dd<v����a�_�S?��0Mo��䷤Ҕ�Ҙ�w��\>�iA�TYL�I�$_��z�      4   '   x�3�4�2�4�2bN.3 6bSNc 6����� U�z      5   1   x���  ���0&�˸�6>���	C)餓6m�|��S�p���+m      7   P   x��;� 뷇1�_��K�5�V����$3�C�j-��2�y��t��ώZdB`�S��k��/d�}RYqX���ND^a�{      9   E   x�3�tt����tL����,.)JL�/RHI�Q(�Rs��8}<���"9�2�r2�KR��2�b���� ��     