#fksadhfkjsdhfkjsdahfkjhsdkjhadsfkjh
#kfhsdkjfhkjsdfhkjsdhk


INSERT INTO user (login, senha) VALUES ( 'admin', 'admin', '9611' ) ON CONFLICT DO NOTHING;

CREATE TABLE caixa( 
      idcaixa       SERIAL      NOT NULL  , 
      valor         text        NOT NULL  ,
      descricao     text        NOT NULL  ,
 PRIMARY KEY (idcaixa)) ; 

CREATE TABLE movimento( 
      idmovimento  SERIAL       NOT NULL  , 
      dt_entrada   text         NOT NULL  , 
      dt_saida     text         NOT NULL  ,
      veiculo_id   integer      NOT NULL,
 PRIMARY KEY (idmovimento)) ; 

CREATE TABLE pessoa( 
      idpessoa      SERIAL      NOT NULL  , 
      nome          text        NOT NULL  , 
      endereco      text        NOT NULL  , 
      CPF           text        NOT NULL  , 
      idade         integer     NOT NULL  , 
      vip_id        integer     NOT NULL  ,
      logradouro    text        NOT NULL  ,
 PRIMARY KEY (idpessoa)) ; 

CREATE TABLE usuario( 
      idusuario     integer     NOT NULL  , 
      Login         text        NOT NULL  , 
      senha         text        NOT NULL  , 
 PRIMARY KEY (idusuario)) ; 

CREATE TABLE vaga( 
      idvaga        SERIAL      NOT NULL  , 
      pessoa_id     int         NOT NULL  ,
      veiculo_id    int         NOT NULL  ,
      movimento_id  int         NOT NULL  ,
      vip_id        int         NOT NULL  ,
      caixa_id      float       NOT NULL  ,
 PRIMARY KEY (idvaga)) ; 

CREATE TABLE veiculo( 
      idveiculo     SERIAL      NOT NULL  , 
      placa         text        NOT NULL  , 
      modelo        text        NOT NULL  ,
      pessoa_id     integer     NOT NULL  , 
 PRIMARY KEY (idveiculo)) ; 

CREATE TABLE vip( 
      idvip     SERIAL          NOT NULL  , 
      descricao text            NOT NULL  , 
 PRIMARY KEY (idvip)) ;

CREATE TABLE limpeza( 
      idlimpeza     SERIAL      NOT NULL  , 
      veiculo_id    int         NOT NULL  ,
 PRIMARY KEY (idlimpeza)) ; 
 
ALTER TABLE usuario   ADD UNIQUE (idUsuario);
  
ALTER TABLE limpeza   ADD CONSTRAINT fk_limpeza_1   FOREIGN KEY (veiculo_id)        references veiculo(idveiculo);
ALTER TABLE pessoa    ADD CONSTRAINT fk_pessoa_1    FOREIGN KEY (vip_id)            references vip(idvip);
ALTER TABLE movimento ADD CONSTRAINT fk_movimento_1 FOREIGN KEY (veiculo_id)        references veiculo(idveiculo);
ALTER TABLE veiculo   ADD CONSTRAINT fk_veiculo_1   FOREIGN KEY (pessoa_id)         references pessoa(idpessoa);
ALTER TABLE vaga      ADD CONSTRAINT fk_vaga_1      FOREIGN KEY (movimento_id)      references movimento(idmovimento);
ALTER TABLE vaga      ADD CONSTRAINT fk_vaga_2      FOREIGN KEY (veiculo_id)        references veiculo(idveiculo);
ALTER TABLE vaga      ADD CONSTRAINT fk_vaga_3      FOREIGN KEY (pessoa_id)         references pessoa(idpessoa);
ALTER TABLE vaga      ADD CONSTRAINT fk_vaga_4      FOREIGN KEY (vip_id)            references vip(idvip);
ALTER TABLE vaga      ADD CONSTRAINT fk_vaga_5      FOREIGN KEY (caixa_id)          references caixa(idcaixa);

SELECT setval('caixa_idCaixa_seq', coalesce(max(idCaixa),0) + 1, false) FROM caixa;
SELECT setval('movimento_idMovimento_seq', coalesce(max(idMovimento),0) + 1, false) FROM movimento;
SELECT setval('pessoa_idPessoa_seq', coalesce(max(idPessoa),0) + 1, false) FROM pessoa;
SELECT setval('vaga_idVaga_seq', coalesce(max(idVaga),0) + 1, false) FROM vaga;
SELECT setval('veiculo_idVeiculo_seq', coalesce(max(idVeiculo),0) + 1, false) FROM veiculo;
SELECT setval('vip_idVip_seq', coalesce(max(idVip),0) + 1, false) FROM vip;