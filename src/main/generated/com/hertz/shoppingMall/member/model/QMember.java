package com.hertz.shoppingMall.member.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1116843745L;

    public static final QMember member = new QMember("member1");

    public final com.hertz.shoppingMall.config.jpa.QBaseDateEntity _super = new com.hertz.shoppingMall.config.jpa.QBaseDateEntity(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath cellNo = createString("cellNo");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDateTime = _super.createdDateTime;

    public final ListPath<com.hertz.shoppingMall.product.model.Product, com.hertz.shoppingMall.product.model.QProduct> createdProducts = this.<com.hertz.shoppingMall.product.model.Product, com.hertz.shoppingMall.product.model.QProduct>createList("createdProducts", com.hertz.shoppingMall.product.model.Product.class, com.hertz.shoppingMall.product.model.QProduct.class, PathInits.DIRECT2);

    public final StringPath emailAddress = createString("emailAddress");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loginId = createString("loginId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDateTime = _super.modifiedDateTime;

    public final ListPath<com.hertz.shoppingMall.product.model.Product, com.hertz.shoppingMall.product.model.QProduct> modifiedProducts = this.<com.hertz.shoppingMall.product.model.Product, com.hertz.shoppingMall.product.model.QProduct>createList("modifiedProducts", com.hertz.shoppingMall.product.model.Product.class, com.hertz.shoppingMall.product.model.QProduct.class, PathInits.DIRECT2);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath realAddress = createString("realAddress");

    public final StringPath region = createString("region");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

