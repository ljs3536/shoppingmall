package com.hertz.shoppingMall.ml.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMLModel is a Querydsl query type for MLModel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMLModel extends EntityPathBase<MLModel> {

    private static final long serialVersionUID = -2012378936L;

    public static final QMLModel mLModel = new QMLModel("mLModel");

    public final com.hertz.shoppingMall.config.jpa.QBaseDateEntity _super = new com.hertz.shoppingMall.config.jpa.QBaseDateEntity(this);

    public final BooleanPath active = createBoolean("active");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDateTime = _super.createdDateTime;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDateTime = _super.modifiedDateTime;

    public final StringPath name = createString("name");

    public final EnumPath<ModelType> type = createEnum("type", ModelType.class);

    public QMLModel(String variable) {
        super(MLModel.class, forVariable(variable));
    }

    public QMLModel(Path<? extends MLModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMLModel(PathMetadata metadata) {
        super(MLModel.class, metadata);
    }

}

