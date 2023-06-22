CREATE TABLE accounts (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   registration_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   uuid UUID NOT NULL,
   profile_data_id BIGINT NOT NULL,
   player_state_id BIGINT NOT NULL,
   CONSTRAINT pk_accounts PRIMARY KEY (id)
);

CREATE TABLE inventory_items (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   player_state_id BIGINT NOT NULL,
   slot INTEGER NOT NULL,
   item_id INTEGER NOT NULL,
   meta INTEGER NOT NULL,
   count INTEGER NOT NULL,
   CONSTRAINT pk_inventory_items PRIMARY KEY (id)
);

CREATE TABLE login_history (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   account_id BIGINT NOT NULL,
   datetime TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   address VARCHAR(255) NOT NULL,
   uuid UUID NOT NULL,
   username VARCHAR(255) NOT NULL,
   client_uuid UUID NOT NULL,
   xuid VARCHAR(255),
   identity_public_key VARCHAR(255) NOT NULL,
   client_id BIGINT NOT NULL,
   server_address VARCHAR(255) NOT NULL,
   device_model VARCHAR(255) NOT NULL,
   device_os INTEGER NOT NULL,
   device_id VARCHAR(255) NOT NULL,
   game_version VARCHAR(255) NOT NULL,
   gui_scale INTEGER NOT NULL,
   language_code VARCHAR(255) NOT NULL,
   current_input_mode INTEGER NOT NULL,
   default_input_mode INTEGER NOT NULL,
   CONSTRAINT pk_login_history PRIMARY KEY (id)
);

CREATE TABLE player_effects (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   player_state_id BIGINT NOT NULL,
   effect_id INTEGER NOT NULL,
   amplifier INTEGER NOT NULL,
   duration INTEGER NOT NULL,
   visible BOOLEAN NOT NULL,
   ambient BOOLEAN NOT NULL,
   CONSTRAINT pk_player_effects PRIMARY KEY (id)
);

CREATE TABLE player_state (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   x DOUBLE PRECISION NOT NULL,
   y DOUBLE PRECISION NOT NULL,
   z DOUBLE PRECISION NOT NULL,
   level_name VARCHAR(255) NOT NULL,
   yaw DOUBLE PRECISION NOT NULL,
   pitch DOUBLE PRECISION NOT NULL,
   head_yaw DOUBLE PRECISION NOT NULL,
   health FLOAT NOT NULL,
   max_health INTEGER NOT NULL,
   experience INTEGER NOT NULL,
   experience_level INTEGER NOT NULL,
   food_level INTEGER NOT NULL,
   saturation_level FLOAT NOT NULL,
   held_item_index INTEGER NOT NULL,
   CONSTRAINT pk_player_state PRIMARY KEY (id)
);

CREATE TABLE profile_data (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   sex VARCHAR(255) NOT NULL,
   age INTEGER NOT NULL,
   CONSTRAINT pk_profile_data PRIMARY KEY (id)
);

ALTER TABLE accounts ADD CONSTRAINT uc_accounts_name UNIQUE (name);

ALTER TABLE accounts ADD CONSTRAINT FK_ACCOUNTS_ON_PLAYER_STATE FOREIGN KEY (player_state_id) REFERENCES player_state (id);

ALTER TABLE accounts ADD CONSTRAINT FK_ACCOUNTS_ON_PROFILE_DATA FOREIGN KEY (profile_data_id) REFERENCES profile_data (id);

ALTER TABLE inventory_items ADD CONSTRAINT FK_INVENTORY_ITEMS_ON_PLAYER_STATE FOREIGN KEY (player_state_id) REFERENCES player_state (id);

ALTER TABLE login_history ADD CONSTRAINT FK_LOGIN_HISTORY_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES accounts (id);

ALTER TABLE player_effects ADD CONSTRAINT FK_PLAYER_EFFECTS_ON_PLAYER_STATE FOREIGN KEY (player_state_id) REFERENCES player_state (id);