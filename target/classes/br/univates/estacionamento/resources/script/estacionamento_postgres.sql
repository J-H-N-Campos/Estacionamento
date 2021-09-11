#fksadhfkjsdhfkjsdahfkjhsdkjhadsfkjh
#kfhsdkjfhkjsdfhkjsdhk


INSERT INTO user (login, senha) VALUES ( 'admin', 'admin', '9611' ) ON CONFLICT DO NOTHING;

CREATE TABLE caixa( 
      idCaixa  SERIAL    NOT NULL  , 
      movimento_id integer   NOT NULL  , 
      saldoTotal float   NOT NULL  , 
 PRIMARY KEY (idCaixa)) ; 

CREATE TABLE movimento( 
      idMovimento  SERIAL    NOT NULL  , 
      veiculo_id integer   NOT NULL  , 
      vaga_id integer   NOT NULL  , 
      dtEntrada timestamp   NOT NULL  , 
      dt_Saida timestamp   NOT NULL  , 
 PRIMARY KEY (idMovimento)) ; 

CREATE TABLE pessoa( 
      idPessoa  SERIAL    NOT NULL  , 
      nome text   NOT NULL  , 
      endereco text   NOT NULL  , 
      CPF text   NOT NULL  , 
      idade integer   NOT NULL  , 
      vip_id integer   NOT NULL  , 
 PRIMARY KEY (idPessoa)) ; 

CREATE TABLE usuario( 
      idUsuario integer   NOT NULL  , 
      Login text   NOT NULL  , 
      senha text   NOT NULL  , 
 PRIMARY KEY (idUsuario)) ; 

CREATE TABLE vaga( 
      idVaga  SERIAL    NOT NULL  , 
      veiculo_categoria_id integer   NOT NULL  , 
      qtdVagas integer   NOT NULL  , 
      preco float   NOT NULL  , 
 PRIMARY KEY (idVaga)) ; 

CREATE TABLE veiculo( 
      idVeiculo  SERIAL    NOT NULL  , 
      placa text   NOT NULL  , 
      modelo text   , 
 PRIMARY KEY (idVeiculo)) ; 

CREATE TABLE vip( 
      idVip  SERIAL    NOT NULL  , 
      descricao text   NOT NULL  , 
 PRIMARY KEY (idVip)) ; 

 
 ALTER TABLE usuario ADD UNIQUE (idUsuario);
  
 ALTER TABLE caixa ADD CONSTRAINT fk_caixa_1 FOREIGN KEY (movimento_id) references movimento(idMovimento); 
ALTER TABLE movimento ADD CONSTRAINT fk_movimento_1 FOREIGN KEY (veiculo_id) references veiculo(idVeiculo); 
ALTER TABLE movimento ADD CONSTRAINT fk_movimento_2 FOREIGN KEY (vaga_id) references vaga(idVaga); 
ALTER TABLE pessoa ADD CONSTRAINT fk_pessoa_1 FOREIGN KEY (vip_id) references vip(idVip); 

  
SELECT setval('caixa_idCaixa_seq', coalesce(max(idCaixa),0) + 1, false) FROM caixa;
SELECT setval('movimento_idMovimento_seq', coalesce(max(idMovimento),0) + 1, false) FROM movimento;
SELECT setval('pessoa_idPessoa_seq', coalesce(max(idPessoa),0) + 1, false) FROM pessoa;
SELECT setval('vaga_idVaga_seq', coalesce(max(idVaga),0) + 1, false) FROM vaga;
SELECT setval('veiculo_idVeiculo_seq', coalesce(max(idVeiculo),0) + 1, false) FROM veiculo;
SELECT setval('vip_idVip_seq', coalesce(max(idVip),0) + 1, false) FROM vip;