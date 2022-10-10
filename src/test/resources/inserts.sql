INSERT INTO vehicle_type (id, created, version, name, description, image)
VALUES (1, now(), 0, 'Tank', 'Panzer', null);
INSERT INTO vehicle_type (id, created, version, name, description, image)
VALUES (2, now(), 0, 'Ship', 'Schiffe', null);

INSERT INTO public.vehicle (id, created, version, name, image, description, vehicle_type_id)
VALUES (1, now(), 0, 'Nimitz',
        'https://www.researchgate.net/profile/Cezary-Szczepanski/publication/336730022/figure/fig1/AS:819298394198017@1572347367763/Nimitz-class-aircraft-carrier-Pixabay.png',
        'Träger', 2);
INSERT INTO public.vehicle (id, created, version, name, image, description, vehicle_type_id)
VALUES (2, now(), 0, 'Pegasus', null, 'Schnellboot', 2);

INSERT INTO public.vehicle_property (id, created, version, name, unit_short, sort_index,
                                     vehicle_type_id)
VALUES (1, now(), 0, 'Knoten', 'kn', 0, 2);
INSERT INTO public.vehicle_property (id, created, version, name, unit_short, sort_index,
                                     vehicle_type_id)
VALUES (2, now(), 0, 'Länge', 'm', 1, 2);
INSERT INTO public.vehicle_property (id, created, version, name, unit_short, sort_index,
                                     vehicle_type_id)
VALUES (3, now(), 0, 'Breite', 'm', 2, 2);
INSERT INTO public.vehicle_property (id, created, version, name, unit_short, sort_index,
                                     vehicle_type_id)
VALUES (4, now(), 0, 'Leistung', 'PS', 3, 2);

INSERT INTO public.vehicle_data (id, created, version, value, vehicle_property_id, vehicle_id)
VALUES (1, now(), 0, '35', 1, 1);
INSERT INTO public.vehicle_data (id, created, version, value, vehicle_property_id, vehicle_id)
VALUES (2, now(), 0, '330', 2, 1);
INSERT INTO public.vehicle_data (id, created, version, value, vehicle_property_id, vehicle_id) VALUES (3, now(), 0, '64', 3, 1);
INSERT INTO public.vehicle_data (id, created, version, value, vehicle_property_id, vehicle_id) VALUES (4, now(), 0, '260000', 4, 1);
