/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.sbml.libsbml;

/** 
 *  An SBML <em>initial assignment</em>, evaluated once only.
 <p>
 * SBML Level 2 Versions 2&ndash;4 and SBML Level&nbsp;3 provide two ways of assigning initial
 * values to entities in a model.  The simplest and most basic is to set
 * the values of the appropriate attributes in the relevant components; for
 * example, the initial value of a model parameter (whether it is a
 * constant or a variable) can be assigned by setting its 'value' attribute
 * directly in the model definition.  However, this approach is not
 * suitable when the value must be calculated, because the initial value
 * attributes on different components such as species, compartments, and
 * parameters are single values and not mathematical expressions.  In those
 * situations, the {@link InitialAssignment} construct can be used; it permits the
 * calculation of the value of a constant or the initial value of a
 * variable from the values of <em>other</em> quantities in a model.
 <p>
 * As explained below, the provision of {@link InitialAssignment} does not mean
 * that models necessarily must use this construct when defining initial
 * values of quantities in a model.  If a value can be set directly using
 * the relevant attribute of a component in a model, then that
 * approach may be more efficient and more portable to other software
 * tools.  {@link InitialAssignment} should be used when the other mechanism is
 * insufficient for the needs of a particular model.
 <p>
 * The {@link InitialAssignment} construct has some similarities to {@link AssignmentRule}.
 * The main differences are: (a) an {@link InitialAssignment} can set the value of
 * a constant whereas an {@link AssignmentRule} cannot, and (b) unlike
 * {@link AssignmentRule}, an {@link InitialAssignment} definition only applies up to and
 * including the beginning of simulation time, i.e., <em>t &#8804; 0</em>,
 * while an {@link AssignmentRule} applies at all times.
 <p>
 * {@link InitialAssignment} has a required attribute, 'symbol', whose value must
 * follow the guidelines for identifiers described in the SBML
 * specification (e.g., Section 3.3 in the Level 2 Version 4
 * specification).  The value of this attribute in an {@link InitialAssignment}
 * object can be the identifier of a {@link Compartment}, {@link Species}, {@link SpeciesReference} 
 * (in SBML Level&nbsp;3),  global {@link Parameter}, or (as of SBML 
 * Level&nbsp;3 Version&nbsp;2) the identifier of a SBML Level&nbsp;3 
 * package element with mathematical meaning.  The {@link InitialAssignment} defines the
 * initial value of the constant or variable referred to by the 'symbol'
 * attribute.  (The attribute's name is 'symbol' rather than 'variable'
 * because it may assign values to constants as well as variables in a
 * model.)  Note that an initial assignment cannot be made to reaction
 * identifiers, that is, the 'symbol' attribute value of an
 * {@link InitialAssignment} cannot be an identifier that is the 'id' attribute
 * value of a {@link Reaction} object in the model.  This is identical to a
 * restriction placed on rules.
 <p>
 * {@link InitialAssignment} also has a 'math' subelement that contains a
 * MathML expression used to calculate the value of the constant or the
 * initial value of the variable.  This subelement is required in SBML
 * Level&nbsp;2 and SBML Level&nbsp;3 Version&nbsp;1, but the requirement
 * was relaxed in SBML Level&nbsp;3 Version&nbsp;2, making it optional.
 * The units of the value computed by the formula in the 'math' subelement 
 * should (in SBML Level&nbsp;2 Version&nbsp;4 and in SBML Level&nbsp;3) 
 * or must (in previous Versions) be identical to be the
 * units associated with the identifier given in the 'symbol' attribute.
 * (That is, the units are the units of the species, compartment, or
 * parameter, as appropriate for the kind of object identified by the value
 * of 'symbol'.)
 <p>
 * {@link InitialAssignment} was introduced in SBML Level 2 Version 2.  It is not
 * available in SBML Level&nbsp;2 Version&nbsp;1 nor in any version of Level 1.
 <p>
 * <h2>Semantics of Initial Assignments</h2>
 <p>
 * The value calculated by an {@link InitialAssignment} object overrides the value
 * assigned to the given symbol by the object defining that symbol.  For
 * example, if a compartment's 'size' attribute is set in its definition,
 * and the model also contains an {@link InitialAssignment} having that
 * compartment's identifier as its 'symbol' attribute value, then the
 * interpretation is that the 'size' assigned in the {@link Compartment} object
 * should be ignored and the value assigned based on the computation
 * defined in the {@link InitialAssignment}.  Initial assignments can take place
 * for {@link Compartment}, {@link Species}, global {@link Parameter}, {@link SpeciesReference} (in 
 * Level&nbsp;3), and SBML Level&nbsp;3 package elements (in 
 * Level&nbsp;3 Version&nbsp;2), regardless of the value of their 
 * 'constant' attribute.
 <p>
 * The actions of all {@link InitialAssignment} objects are in general terms
 * the same, but differ in the precise details depending on the type
 * of variable being set:
 * <ul>
 * <li> <em>In the case of a species</em>, an {@link InitialAssignment} sets the
 * referenced species' initial quantity (concentration or amount of
 * substance) to the value determined by the formula in the 'math'
 * subelement.    The overall units of the formula should (in SBML
 * Level&nbsp;2 Version&nbsp;4 and in SBML Level&nbsp;3) or must (in previous Versions) be the same
 * as the units specified for the species.
 <p>
 * <li> <em>In the case of a compartment</em>, an {@link InitialAssignment} sets
 * the referenced compartment's initial size to the size determined by the
 * formula in 'math'.  The overall units of the formula should (in SBML
 * Level&nbsp;2 Version&nbsp;4 and in SBML Level&nbsp;3) or must (in previous Versions) be the same
 * as the units specified for the size of the compartment.
 <p>
 * <li> <em>In the case of a parameter</em>, an {@link InitialAssignment} sets the
 * referenced parameter's initial value to that determined by the formula
 * in 'math'.  The overall units of the formula should (in SBML
 * Level&nbsp;2 Version&nbsp;4 and SBML Level&nbsp;3) or must (in previous Versions) be the same
 * as the units defined for the parameter.  
 <p>
 * <li> (For SBML Level&nbsp;3 only) <em>In the case of a species
 * reference</em>, an initial assignment sets the initial value of the 
 * stoichiometry of the referenced reactant or product to the value determined 
 * by the formula in 'math'.  The unit associated with the value produced by 
 * the 'math' formula should be consistent with the unit 'dimensionless',
 * because reactant and product stoichiometries in reactions are dimensionless
 * quantities.
 <p>
 * <li>(For SBML Level&nbsp;3 Version&nbsp;2 only) <em>In the case 
 * of an object from an SBML Level&nbsp;3 package</em>, an {@link InitialAssignment} 
 * sets the referenced object's initial value (however such values are 
 * defined by the package) to the value of the formula in math. The unit 
 * of measurement associated with the value produced by the formula 
 * should be the same as that object's units attribute value (if it has 
 * such an attribute), or be equal to the units of model components of 
 * that type (if objects of that class are defined by the package as 
 * having the same units).
 <p>
 * </ul>
 <p>
 * If the symbol attribute of an {@link InitialAssignment} object references 
 * an object in an SBML namespace that is not understood by the 
 * interpreter reading a given SBML document (that is, if the object 
 * is defined by an SBML Level&nbsp;3 package that the software does 
 * not support), the assignment must be ignored--the object's initial 
 * value will not need to be set, as the interpreter could not understand 
 * that package. If an interpreter cannot establish whether a referenced 
 * object is missing from the model or instead is defined in an SBML 
 * namespace not understood by the interpreter, it may produce a 
 * warning to the user. (The latter situation may only arise if an SBML 
 * package is present in the SBML document with a package:required 
 * attribute of 'true'.)
 <p>
 * In the context of a simulation, initial assignments establish values
 * that are in effect prior to and including the start of simulation time,
 * i.e., <em>t &#8804; 0</em>.  Section 3.4.8 in the SBML Level&nbsp;2
 * Version&nbsp;4  and SBML Level&nbsp;3 specifications 
 * provides information about the interpretation of
 * assignments, rules, and entity values for simulation time up to and
 * including the start time <em>t = 0</em>; this is important for
 * establishing the initial conditions of a simulation if the model
 * involves expressions containing the <em>delay</em> 'csymbol'.
 <p>
 * There cannot be two initial assignments for the same symbol in a model;
 * that is, a model must not contain two or more {@link InitialAssignment} objects
 * that both have the same identifier as their 'symbol' attribute value.  A
 * model must also not define initial assignments <em>and</em> assignment
 * rules for the same entity.  That is, there cannot be <em>both</em> an
 * {@link InitialAssignment} and an {@link AssignmentRule} for the same symbol in a model,
 * because both kinds of constructs apply prior to and at the start of
 * simulated time&mdash;allowing both to exist for a given symbol would
 * result in indeterminism).
 <p>
 * The ordering of {@link InitialAssignment} objects is not significant.  The
 * combined set of {@link InitialAssignment}, {@link AssignmentRule} and {@link KineticLaw}
 * objects form a set of assignment statements that must be considered as a
 * whole.  The combined set of assignment statements should not contain
 * algebraic loops: a chain of dependency between these statements should
 * terminate.  (More formally, consider the directed graph of assignment
 * statements where nodes are a model's assignment statements and directed
 * arcs exist for each occurrence of a symbol in an assignment statement
 * 'math' attribute.  The directed arcs in this graph start from the
 * statement assigning the symbol and end at the statement that contains
 * the symbol in their math elements.  Such a graph must be acyclic.)
 <p>
 * Finally, it is worth being explicit about the expected behavior in the
 * following situation.  Suppose (1) a given symbol has a value <em>x</em>
 * assigned to it in its definition, and (2) there is an initial assignment
 * having the identifier as its 'symbol' value and reassigning the value to
 * <em>y</em>, <em>and</em> (3) the identifier is also used in the
 * mathematical formula of a second initial assignment.  What value should
 * the second initial assignment use?  It is <em>y</em>, the value assigned
 * to the symbol by the first initial assignment, not whatever value was
 * given in the symbol's definition.  This follows directly from the
 * behavior described above: if an {@link InitialAssignment} object exists for a
 * given symbol, then the symbol's value is overridden by that initial
 * assignment.
 */

public class InitialAssignment extends SBase {
   private long swigCPtr;

   protected InitialAssignment(long cPtr, boolean cMemoryOwn)
   {
     super(libsbmlJNI.InitialAssignment_SWIGUpcast(cPtr), cMemoryOwn);
     swigCPtr = cPtr;
   }

   protected static long getCPtr(InitialAssignment obj)
   {
     return (obj == null) ? 0 : obj.swigCPtr;
   }

   protected static long getCPtrAndDisown (InitialAssignment obj)
   {
     long ptr = 0;

     if (obj != null)
     {
       ptr             = obj.swigCPtr;
       obj.swigCMemOwn = false;
     }

     return ptr;
   }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libsbmlJNI.delete_InitialAssignment(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  
/**
   * Creates a new {@link InitialAssignment} using the given SBML <code>level</code> and <code>version</code>
   * values.
   <p>
   * @param level a long integer, the SBML Level to assign to this {@link InitialAssignment}.
   <p>
   * @param version a long integer, the SBML Version to assign to this
   * {@link InitialAssignment}.
   <p>
   * <p>
 * @throws SBMLConstructorException
 * Thrown if the given <code>level</code> and <code>version</code> combination are invalid
 * or if this object is incompatible with the given level and version.
   <p>
   * <p>
 * @note Attempting to add an object to an {@link SBMLDocument} having a different
 * combination of SBML Level, Version and XML namespaces than the object
 * itself will result in an error at the time a caller attempts to make the
 * addition.  A parent object must have compatible Level, Version and XML
 * namespaces.  (Strictly speaking, a parent may also have more XML
 * namespaces than a child, but the reverse is not permitted.)  The
 * restriction is necessary to ensure that an SBML model has a consistent
 * overall structure.  This requires callers to manage their objects
 * carefully, but the benefit is increased flexibility in how models can be
 * created by permitting callers to create objects bottom-up if desired.  In
 * situations where objects are not yet attached to parents (e.g.,
 * {@link SBMLDocument}), knowledge of the intented SBML Level and Version help
 * libSBML determine such things as whether it is valid to assign a
 * particular value to an attribute.
   */ public
 InitialAssignment(long level, long version) throws org.sbml.libsbml.SBMLConstructorException {
    this(libsbmlJNI.new_InitialAssignment__SWIG_0(level, version), true);
  }

  
/**
   * Creates a new {@link InitialAssignment} using the given {@link SBMLNamespaces} object
   * <code>sbmlns</code>.
   <p>
   * <p>
 * The {@link SBMLNamespaces} object encapsulates SBML Level/Version/namespaces
 * information.  It is used to communicate the SBML Level, Version, and (in
 * Level&nbsp;3) packages used in addition to SBML Level&nbsp;3 Core.  A
 * common approach to using libSBML's {@link SBMLNamespaces} facilities is to create an
 * {@link SBMLNamespaces} object somewhere in a program once, then hand that object
 * as needed to object constructors that accept {@link SBMLNamespaces} as arguments. 
   <p>
   * @param sbmlns an {@link SBMLNamespaces} object.
   <p>
   * <p>
 * @throws SBMLConstructorException
 * Thrown if the given <code>sbmlns</code> is inconsistent or incompatible
 * with this object.
   <p>
   * <p>
 * @note Attempting to add an object to an {@link SBMLDocument} having a different
 * combination of SBML Level, Version and XML namespaces than the object
 * itself will result in an error at the time a caller attempts to make the
 * addition.  A parent object must have compatible Level, Version and XML
 * namespaces.  (Strictly speaking, a parent may also have more XML
 * namespaces than a child, but the reverse is not permitted.)  The
 * restriction is necessary to ensure that an SBML model has a consistent
 * overall structure.  This requires callers to manage their objects
 * carefully, but the benefit is increased flexibility in how models can be
 * created by permitting callers to create objects bottom-up if desired.  In
 * situations where objects are not yet attached to parents (e.g.,
 * {@link SBMLDocument}), knowledge of the intented SBML Level and Version help
 * libSBML determine such things as whether it is valid to assign a
 * particular value to an attribute.
   */ public
 InitialAssignment(SBMLNamespaces sbmlns) throws org.sbml.libsbml.SBMLConstructorException {
    this(libsbmlJNI.new_InitialAssignment__SWIG_1(SBMLNamespaces.getCPtr(sbmlns), sbmlns), true);
  }

  
/**
   * Copy constructor; creates a copy of this {@link InitialAssignment}.
   <p>
   * @param orig the object to copy.
   */ public
 InitialAssignment(InitialAssignment orig) throws org.sbml.libsbml.SBMLConstructorException {
    this(libsbmlJNI.new_InitialAssignment__SWIG_2(InitialAssignment.getCPtr(orig), orig), true);
  }

  
/**
   * Creates and returns a deep copy of this {@link InitialAssignment} object.
   <p>
   * @return the (deep) copy of this {@link InitialAssignment} object.
   */ public
 InitialAssignment cloneObject() {
    long cPtr = libsbmlJNI.InitialAssignment_cloneObject(swigCPtr, this);
    return (cPtr == 0) ? null : new InitialAssignment(cPtr, true);
  }

  
/**
   * Get the value of the 'symbol' attribute of this {@link InitialAssignment}.
   <p>
   * @return the identifier string stored as the 'symbol' attribute value
   * in this {@link InitialAssignment}.
   */ public
 String getSymbol() {
    return libsbmlJNI.InitialAssignment_getSymbol(swigCPtr, this);
  }

  
/**
   * Get the mathematical formula of this {@link InitialAssignment}.
   <p>
   * @return an {@link ASTNode}, the value of the 'math' subelement of this
   * {@link InitialAssignment}, or <code>null</code> if the math is not set.
   */ public
 ASTNode getMath() {
    long cPtr = libsbmlJNI.InitialAssignment_getMath(swigCPtr, this);
    return (cPtr == 0) ? null : new ASTNode(cPtr, false);
  }

  
/**
   * Predicate returning <code>true</code> if this
   * {@link InitialAssignment}'s 'symbol' attribute is set.
   <p>
   * @return <code>true</code> if the 'symbol' attribute of this {@link InitialAssignment}
   * is set, <code>false</code> otherwise.
   */ public
 boolean isSetSymbol() {
    return libsbmlJNI.InitialAssignment_isSetSymbol(swigCPtr, this);
  }

  
/**
   * Predicate returning <code>true</code> if this
   * {@link InitialAssignment}'s 'math' subelement contains a value.
   <p>
   * @return <code>true</code> if the 'math' for this {@link InitialAssignment} is set,
   * <code>false</code> otherwise.
   */ public
 boolean isSetMath() {
    return libsbmlJNI.InitialAssignment_isSetMath(swigCPtr, this);
  }

  
/**
   * Sets the 'symbol' attribute value of this {@link InitialAssignment}.
   <p>
   * @param sid the identifier of an element defined in this model whose
   * value can be set.
   <p>
   * <p>
 * @return integer value indicating success/failure of the
 * function.   The possible values
 * returned by this function are:
   * <ul>
   * <li> {@link libsbmlConstants#LIBSBML_OPERATION_SUCCESS LIBSBML_OPERATION_SUCCESS}
   * <li> {@link libsbmlConstants#LIBSBML_INVALID_ATTRIBUTE_VALUE LIBSBML_INVALID_ATTRIBUTE_VALUE}
   * </ul>
   */ public
 int setSymbol(String sid) {
    return libsbmlJNI.InitialAssignment_setSymbol(swigCPtr, this, sid);
  }

  
/**
   * Unsets the 'symbol' attribute value of this {@link InitialAssignment}.
   <p>
   * <p>
 * @return integer value indicating success/failure of the
 * function.   The possible values
 * returned by this function are:
   * <ul>
   * <li> {@link libsbmlConstants#LIBSBML_OPERATION_SUCCESS LIBSBML_OPERATION_SUCCESS}
   * <li> {@link libsbmlConstants#LIBSBML_INVALID_ATTRIBUTE_VALUE LIBSBML_INVALID_ATTRIBUTE_VALUE}
   * </ul>
   */ public
 int unsetSymbol() {
    return libsbmlJNI.InitialAssignment_unsetSymbol(swigCPtr, this);
  }

  
/**
   * Sets the 'math' subelement of this {@link InitialAssignment}.
   <p>
   * The AST passed in <code>math</code> is copied.
   <p>
   * @param math an AST containing the mathematical expression to
   * be used as the formula for this {@link InitialAssignment}.
   <p>
   * <p>
 * @return integer value indicating success/failure of the
 * function.   The possible values
 * returned by this function are:
   * <ul>
   * <li> {@link libsbmlConstants#LIBSBML_OPERATION_SUCCESS LIBSBML_OPERATION_SUCCESS}
   * <li> {@link libsbmlConstants#LIBSBML_INVALID_OBJECT LIBSBML_INVALID_OBJECT}
   * </ul>
   */ public
 int setMath(ASTNode math) {
    return libsbmlJNI.InitialAssignment_setMath(swigCPtr, this, ASTNode.getCPtr(math), math);
  }

  
/**
   * Calculates and returns a {@link UnitDefinition} that expresses the units
   * of measurement assumed for the 'math' expression of this
   * {@link InitialAssignment}.
   <p>
   * <p>
 * The units are calculated based on the mathematical expression in the
 * {@link InitialAssignment} and the model quantities referenced by
 * <code>&lt;ci&gt;</code> elements used within that expression.  The method
 * {@link InitialAssignment#getDerivedUnitDefinition()} returns the calculated
 * units, to the extent that libSBML can compute them.
   <p>
   * <p>
 * @note The functionality that facilitates unit analysis depends on the
 * model as a whole.  Thus, in cases where the object has not been added to
 * a model or the model itself is incomplete, unit analysis is not possible
 * and this method will return <code>null.</code> 
   <p>
   * <p>
 * @warning <span class='warning'>Note that it is possible the 'math'
 * expression in the {@link InitialAssignment} contains literal numbers or parameters
 * with undeclared units.  In those cases, it is not possible to calculate
 * the units of the overall expression without making assumptions.  LibSBML
 * does not make assumptions about the units, and
 * {@link InitialAssignment#getDerivedUnitDefinition()} only returns the units as
 * far as it is able to determine them.  For example, in an expression <em>X
 * + Y</em>, if <em>X</em> has unambiguously-defined units and <em>Y</em>
 * does not, it will return the units of <em>X</em>.  When using this method,
 * <strong>it is critical that callers also invoke the method</strong>
 * {@link InitialAssignment#containsUndeclaredUnits()} <strong>to determine whether
 * this situation holds</strong>.  Callers should take suitable action in
 * those situations.</span>
   <p>
   * @return a {@link UnitDefinition} that expresses the units of the math 
   * expression of this {@link InitialAssignment}, or <code>null</code> if one cannot be constructed.
   <p>
   * @see #containsUndeclaredUnits()
   */ public
 UnitDefinition getDerivedUnitDefinition() {
    long cPtr = libsbmlJNI.InitialAssignment_getDerivedUnitDefinition__SWIG_0(swigCPtr, this);
    return (cPtr == 0) ? null : new UnitDefinition(cPtr, false);
  }

  
/**
   * Predicate returning <code>true</code> if the math expression of this
   * {@link InitialAssignment} contains parameters/numbers with undeclared units.
   <p>
   * @return <code>true</code> if the math expression of this {@link InitialAssignment}
   * includes parameters/numbers 
   * with undeclared units, <code>false</code> otherwise.
   <p>
   * @note A return value of <code>true</code> indicates that the {@link UnitDefinition}
   * returned by InitialAssignment.getDerivedUnitDefinition may not
   * accurately represent the units of the expression.
   <p>
   * @see #getDerivedUnitDefinition()
   */ public
 boolean containsUndeclaredUnits() {
    return libsbmlJNI.InitialAssignment_containsUndeclaredUnits__SWIG_0(swigCPtr, this);
  }

  
/**
   * Returns the libSBML type code for this SBML object.
   <p>
   * <p>
 * LibSBML attaches an identifying code to every kind of SBML object.  These
 * are integer constants known as <em>SBML type codes</em>.  The names of all
 * the codes begin with the characters <code>SBML_</code>.
 * In the Java language interface for libSBML, the
 * type codes are defined as static integer constants in the interface class
 * {@link libsbmlConstants}.    Note that different Level&nbsp;3
 * package plug-ins may use overlapping type codes; to identify the package
 * to which a given object belongs, call the 
 * <code>{@link SBase#getPackageName()}
 * </code>
 * method on the object.
   <p>
   * @return the SBML type code for this object:
   * {@link libsbmlConstants#SBML_INITIAL_ASSIGNMENT SBML_INITIAL_ASSIGNMENT} (default).
   <p>
   * <p>
 * @warning <span class='warning'>The specific integer values of the possible
 * type codes may be reused by different libSBML plug-ins for SBML Level&nbsp;3.
 * packages,  To fully identify the correct code, <strong>it is necessary to
 * invoke both getTypeCode() and getPackageName()</strong>.</span>
   <p>
   * @see #getElementName()
   * @see #getPackageName()
   */ public
 int getTypeCode() {
    return libsbmlJNI.InitialAssignment_getTypeCode(swigCPtr, this);
  }

  
/**
   * Returns the XML element name of this object, which for
   * {@link InitialAssignment}, is always <code>'initialAssignment'.</code>
   <p>
   * @return the name of this element, i.e., <code>'initialAssignment'.</code>
   */ public
 String getElementName() {
    return libsbmlJNI.InitialAssignment_getElementName(swigCPtr, this);
  }

  
/**
   * Predicate returning <code>true</code> if all the required attributes for this
   * {@link InitialAssignment} object have been set.
   <p>
   * The required attributes for an {@link InitialAssignment} object are:
   * <ul>
   * <li> 'symbol'
   *
   * </ul> <p>
   * @return <code>true</code> if the required attributes have been set, <code>false</code>
   * otherwise.
   */ public
 boolean hasRequiredAttributes() {
    return libsbmlJNI.InitialAssignment_hasRequiredAttributes(swigCPtr, this);
  }

  
/**
   * Predicate returning <code>true</code> if all the required elements for this
   * {@link InitialAssignment} object have been set.
   <p>
   * @note The required elements for a {@link InitialAssignment} object are:
   * <ul>
   * <li> 'math' inSBML Level&nbsp;2 and Level&nbsp;3 Version&nbsp;1.  
   *     (In SBML Level&nbsp;3 Version&nbsp;2+, it is no longer required.)
   *
   * </ul> <p>
   * @return a boolean value indicating whether all the required
   * elements for this object have been defined.
   */ public
 boolean hasRequiredElements() {
    return libsbmlJNI.InitialAssignment_hasRequiredElements(swigCPtr, this);
  }

  
/**
   * Returns the value of the 'symbol' attribute of this {@link InitialAssignment} (NOT the 'id').
   <p>
   * @note Because of the inconsistent behavior of this function with 
   * respect to assignments and rules, it is now recommended to
   * use the getIdAttribute() or {@link InitialAssignment#getSymbol()} 
   * functions instead.
   <p>
   * The 'symbol' attribute of an {@link InitialAssignment} indicates the element which
   * the results of the 'math' are to be applied.
   <p>
   * @return the symbol of this {@link InitialAssignment}.
   <p>
   * @see #getIdAttribute()
   * @see #setIdAttribute(String sid)
   * @see #isSetIdAttribute()
   * @see #unsetIdAttribute()
   * @see #getSymbol()
   */ public
 String getId() {
    return libsbmlJNI.InitialAssignment_getId(swigCPtr, this);
  }

  
/**
   * <p>
 * Replaces all uses of a given <code>SIdRef</code> type attribute value with another
 * value.
 <p>
 * <p>
 * In SBML, object identifiers are of a data type called <code>SId</code>.
 * In SBML Level&nbsp;3, an explicit data type called <code>SIdRef</code> was
 * introduced for attribute values that refer to <code>SId</code> values; in
 * previous Levels of SBML, this data type did not exist and attributes were
 * simply described to as 'referring to an identifier', but the effective
 * data type was the same as <code>SIdRef</code> in Level&nbsp;3.  These and
 * other methods of libSBML refer to the type <code>SIdRef</code> for all
 * Levels of SBML, even if the corresponding SBML specification did not
 * explicitly name the data type.
 <p>
 * This method works by looking at all attributes and (if appropriate)
 * mathematical formulas in MathML content, comparing the referenced
 * identifiers to the value of <code>oldid</code>.  If any matches are found, the
 * matching values are replaced with <code>newid</code>.  The method does <em>not</em>
 * descend into child elements.
 <p>
 * @param oldid the old identifier.
 * @param newid the new identifier.
   */ public
 void renameSIdRefs(String oldid, String newid) {
    libsbmlJNI.InitialAssignment_renameSIdRefs(swigCPtr, this, oldid, newid);
  }

  
/**
   * <p>
 * Replaces all uses of a given <code>UnitSIdRef</code> type attribute value with
 * another value.
 <p>
 * <p>
 * In SBML, unit definitions have identifiers of type <code>UnitSId</code>.  In
 * SBML Level&nbsp;3, an explicit data type called <code>UnitSIdRef</code> was
 * introduced for attribute values that refer to <code>UnitSId</code> values; in
 * previous Levels of SBML, this data type did not exist and attributes were
 * simply described to as 'referring to a unit identifier', but the effective
 * data type was the same as <code>UnitSIdRef</code> in Level&nbsp;3.  These and
 * other methods of libSBML refer to the type <code>UnitSIdRef</code> for all
 * Levels of SBML, even if the corresponding SBML specification did not
 * explicitly name the data type.
 <p>
 * This method works by looking at all unit identifier attribute values
 * (including, if appropriate, inside mathematical formulas), comparing the
 * referenced unit identifiers to the value of <code>oldid</code>.  If any matches
 * are found, the matching values are replaced with <code>newid</code>.  The method
 * does <em>not</em> descend into child elements.
 <p>
 * @param oldid the old identifier.
 * @param newid the new identifier.
   */ public
 void renameUnitSIdRefs(String oldid, String newid) {
    libsbmlJNI.InitialAssignment_renameUnitSIdRefs(swigCPtr, this, oldid, newid);
  }

  
/** * @internal */ public
 void replaceSIDWithFunction(String id, ASTNode function) {
    libsbmlJNI.InitialAssignment_replaceSIDWithFunction(swigCPtr, this, id, ASTNode.getCPtr(function), function);
  }

  
/** * @internal */ public
 void divideAssignmentsToSIdByFunction(String id, ASTNode function) {
    libsbmlJNI.InitialAssignment_divideAssignmentsToSIdByFunction(swigCPtr, this, id, ASTNode.getCPtr(function), function);
  }

  
/** * @internal */ public
 void multiplyAssignmentsToSIdByFunction(String id, ASTNode function) {
    libsbmlJNI.InitialAssignment_multiplyAssignmentsToSIdByFunction(swigCPtr, this, id, ASTNode.getCPtr(function), function);
  }

}