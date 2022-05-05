PGDMP     2    *                z         	   testNexos    13.6    13.6 (    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16435 	   testNexos    DATABASE     j   CREATE DATABASE "testNexos" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Colombia.1252';
    DROP DATABASE "testNexos";
                postgres    false            �            1255    16645    productsbitacore()    FUNCTION     �   CREATE FUNCTION public.productsbitacore() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin


INSERT INTO logs (date,product_id,user_id) values (now(),new.id,new.user_id);

return new;

END;
$$;
 )   DROP FUNCTION public.productsbitacore();
       public          postgres    false            �            1255    16696    productslogs()    FUNCTION     �   CREATE FUNCTION public.productslogs() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin


INSERT INTO logs (date,product_id,user_id) values (now(),new.id,new.user_id);

return new;

END;
$$;
 %   DROP FUNCTION public.productslogs();
       public          postgres    false            �            1259    16649    charges    TABLE     Z   CREATE TABLE public.charges (
    id integer NOT NULL,
    name character varying(100)
);
    DROP TABLE public.charges;
       public         heap    postgres    false            �            1259    16647    charges_id_seq    SEQUENCE     �   CREATE SEQUENCE public.charges_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.charges_id_seq;
       public          postgres    false    201            �           0    0    charges_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.charges_id_seq OWNED BY public.charges.id;
          public          postgres    false    200            �            1259    16657    logs    TABLE     �   CREATE TABLE public.logs (
    id integer NOT NULL,
    date timestamp without time zone,
    product_id integer,
    user_id integer
);
    DROP TABLE public.logs;
       public         heap    postgres    false            �            1259    16655    logs_id_seq    SEQUENCE     �   CREATE SEQUENCE public.logs_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.logs_id_seq;
       public          postgres    false    203            �           0    0    logs_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.logs_id_seq OWNED BY public.logs.id;
          public          postgres    false    202            �            1259    16665    products    TABLE     �   CREATE TABLE public.products (
    id integer NOT NULL,
    amount integer NOT NULL,
    date date,
    name_product character varying(100),
    user_id integer
);
    DROP TABLE public.products;
       public         heap    postgres    false            �            1259    16663    products_id_seq    SEQUENCE     �   CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.products_id_seq;
       public          postgres    false    205            �           0    0    products_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;
          public          postgres    false    204            �            1259    16673    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    date_ingress date,
    edad integer,
    name character varying(100),
    charge_id integer
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16671    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    207            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    206            6           2604    16652 
   charges id    DEFAULT     h   ALTER TABLE ONLY public.charges ALTER COLUMN id SET DEFAULT nextval('public.charges_id_seq'::regclass);
 9   ALTER TABLE public.charges ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    201    200    201            7           2604    16660    logs id    DEFAULT     b   ALTER TABLE ONLY public.logs ALTER COLUMN id SET DEFAULT nextval('public.logs_id_seq'::regclass);
 6   ALTER TABLE public.logs ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    203    203            8           2604    16668    products id    DEFAULT     j   ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);
 :   ALTER TABLE public.products ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    205    204    205            9           2604    16676    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    207    206    207            �          0    16649    charges 
   TABLE DATA           +   COPY public.charges (id, name) FROM stdin;
    public          postgres    false    201   B+       �          0    16657    logs 
   TABLE DATA           =   COPY public.logs (id, date, product_id, user_id) FROM stdin;
    public          postgres    false    203   �+       �          0    16665    products 
   TABLE DATA           K   COPY public.products (id, amount, date, name_product, user_id) FROM stdin;
    public          postgres    false    205   W,       �          0    16673    users 
   TABLE DATA           H   COPY public.users (id, date_ingress, edad, name, charge_id) FROM stdin;
    public          postgres    false    207   -       �           0    0    charges_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.charges_id_seq', 2, true);
          public          postgres    false    200            �           0    0    logs_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.logs_id_seq', 15, true);
          public          postgres    false    202            �           0    0    products_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.products_id_seq', 10, true);
          public          postgres    false    204            �           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 1, false);
          public          postgres    false    206            ;           2606    16654    charges charges_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.charges
    ADD CONSTRAINT charges_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.charges DROP CONSTRAINT charges_pkey;
       public            postgres    false    201            ?           2606    16662    logs logs_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.logs
    ADD CONSTRAINT logs_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.logs DROP CONSTRAINT logs_pkey;
       public            postgres    false    203            A           2606    16670    products products_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
       public            postgres    false    205            E           2606    16684 "   users uk_3g1j96g94xpk3lpxl2qbl985x 
   CONSTRAINT     ]   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_3g1j96g94xpk3lpxl2qbl985x UNIQUE (name);
 L   ALTER TABLE ONLY public.users DROP CONSTRAINT uk_3g1j96g94xpk3lpxl2qbl985x;
       public            postgres    false    207            =           2606    16680 $   charges uk_m94v9i6nvohwr4agbs3p7hmwv 
   CONSTRAINT     _   ALTER TABLE ONLY public.charges
    ADD CONSTRAINT uk_m94v9i6nvohwr4agbs3p7hmwv UNIQUE (name);
 N   ALTER TABLE ONLY public.charges DROP CONSTRAINT uk_m94v9i6nvohwr4agbs3p7hmwv;
       public            postgres    false    201            C           2606    16682 %   products uk_sgw85sd2bk54y77onr4q2hkqr 
   CONSTRAINT     h   ALTER TABLE ONLY public.products
    ADD CONSTRAINT uk_sgw85sd2bk54y77onr4q2hkqr UNIQUE (name_product);
 O   ALTER TABLE ONLY public.products DROP CONSTRAINT uk_sgw85sd2bk54y77onr4q2hkqr;
       public            postgres    false    205            G           2606    16678    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    207            J           2620    16697    products log_update_insert    TRIGGER     �   CREATE TRIGGER log_update_insert BEFORE INSERT OR UPDATE ON public.products FOR EACH ROW EXECUTE FUNCTION public.productslogs();
 3   DROP TRIGGER log_update_insert ON public.products;
       public          postgres    false    205    209            I           2606    16690 !   users fk4a7ph86ybxdsovym3l33aset1    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk4a7ph86ybxdsovym3l33aset1 FOREIGN KEY (charge_id) REFERENCES public.charges(id);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT fk4a7ph86ybxdsovym3l33aset1;
       public          postgres    false    207    2875    201            H           2606    16685 $   products fkdb050tk37qryv15hd932626th    FK CONSTRAINT     �   ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkdb050tk37qryv15hd932626th FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.products DROP CONSTRAINT fkdb050tk37qryv15hd932626th;
       public          postgres    false    207    205    2887            �   9   x�3�t,N-�/RHIU(K�+I,�2�tL����,.)JL�T*����r��qqq �ZO      �   �   x�e��q1�3�b0E��X�f�fm�n�
Z�TT��}�ڢ��, N ]z���SaY��e7��A�dk_����)������	{�T�yA��Z���/�̆�J��xMŃ� ,�%�S�PP��B[�qNd�![�[�-�sO���`�حɱӋ���
Fm�[Գ�oq�3B4߰��k�?�S^      �   �   x�M�1�0��+���}�Ch�蠁���0Bqd�gQ�	�F��U��{-HIe��ǘ@���q��<A��ݝs�E�h�28�-Ģ����e%�x�)p�4�b%Y�>È���(�_y�}��Ê���$�8<�9��۪��ƎG��7���e-�� �@3�      �   P   x�3�4202�50"N#sN���Լ�|N#.#SNǼ���bNC.c	��Ԥ�⒌��<��	���weNf"д=... �Z     