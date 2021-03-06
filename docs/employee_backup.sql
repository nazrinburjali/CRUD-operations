--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 13.1

-- Started on 2021-02-23 17:28:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 24729)
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    surname character varying(200) NOT NULL,
    salary numeric NOT NULL
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 24727)
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.employee ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2838 (class 0 OID 24729)
-- Dependencies: 209
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee (id, name, surname, salary) FROM stdin;
2	samire	ahmad	724
3	samire	ahmad	724
4	samire	ahmad	72
\.


--
-- TOC entry 2844 (class 0 OID 0)
-- Dependencies: 208
-- Name: employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employee_id_seq', 4, true);


--
-- TOC entry 2710 (class 2606 OID 24736)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


-- Completed on 2021-02-23 17:28:42

--
-- PostgreSQL database dump complete
--

