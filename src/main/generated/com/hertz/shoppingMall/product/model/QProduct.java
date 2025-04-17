package com.hertz.shoppingMall.product.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -46596167L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final com.hertz.shoppingMall.config.jpa.QBaseDateEntity _super = new com.hertz.shoppingMall.config.jpa.QBaseDateEntity(this);

    public final QCategory category;

    public final com.hertz.shoppingMall.member.model.QMember createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDateTime = _super.createdDateTime;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.hertz.shoppingMall.member.model.QMember modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDateTime = _super.modifiedDateTime;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> stockQuantity = createNumber("stockQuantity", Integer.class);

    public final ListPath<com.hertz.shoppingMall.utils.exception.image.model.Image, com.hertz.shoppingMall.utils.exception.image.model.QImage> subImages = this.<com.hertz.shoppingMall.utils.exception.image.model.Image, com.hertz.shoppingMall.utils.exception.image.model.QImage>createList("subImages", com.hertz.shoppingMall.utils.exception.image.model.Image.class, com.hertz.shoppingMall.utils.exception.image.model.QImage.class, PathInits.DIRECT2);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.createdBy = inits.isInitialized("createdBy") ? new com.hertz.shoppingMall.member.model.QMember(forProperty("createdBy")) : null;
        this.modifiedBy = inits.isInitialized("modifiedBy") ? new com.hertz.shoppingMall.member.model.QMember(forProperty("modifiedBy")) : null;
    }

}

